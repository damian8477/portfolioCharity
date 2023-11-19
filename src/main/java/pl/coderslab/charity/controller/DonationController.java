package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service_interface.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final UserService userService;

    @GetMapping("/add")
    public String getAddDonationView(Model model) {
        model.addAttribute("donation", new Donation());
        return "/donation/add";
    }

    @PostMapping("/add")
    public String addDonation(@Valid Donation donation, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("donation", donation);
            return "/donation/add";
        }
        User user = userService.getByEmail(authenticatedUser.getUsername());
        donation.setUser(user);
        donationRepository.save(donation);
        return "/donation/form-confirmation";
    }

    @GetMapping("/list")
    public String getListDonationView(Model model, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User user = userService.getByEmail(authenticatedUser.getUsername());
        List<Donation> donations = donationRepository.findAllByUserId(user.getId());
        donations.sort(Comparator
                .comparing(Donation::isReceived)
                .thenComparing(Donation::getReceivedTime, Comparator.nullsFirst(Comparator.naturalOrder()))
                .thenComparing(Donation::getCreated));
        model.addAttribute("donations", donations);
        return "/donation/list";
    }

    @GetMapping("/confirm")
    public String getConfirmView(Model model, @RequestParam Long donationId) {
        Donation donation = donationRepository.getById(donationId);
        if (!isNull(donation)) {
            model.addAttribute("donation", donation);
            return "donation/confirm-page";
        }
        return "redirect:/donation/list";
    }

    @GetMapping("/details")
    public String getDetailsView(Model model, @RequestParam Long donationId) {
        Donation donation = donationRepository.getById(donationId);
        if (!isNull(donation)) {
            model.addAttribute("donation", donation);
            return "donation/details";
        }
        return "redirect:/donation/list";
    }

    @PostMapping("/confirm")
    public String getConfirm(@RequestParam Long donationId) {
        Donation donation = donationRepository.getById(donationId);
        if (!isNull(donation)) {
            donation.setReceived(true);
            donation.setReceivedTime(LocalDateTime.now());
            donationRepository.save(donation);
        }
        return "redirect:/donation/list";
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionRepository.findAll();
    }
}
