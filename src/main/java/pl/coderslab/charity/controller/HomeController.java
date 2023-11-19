package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("sumPack", donationRepository.getSum());
        model.addAttribute("countDonation", donationRepository.getCount());
        return "index";
    }
}
