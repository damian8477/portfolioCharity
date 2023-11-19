package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service_interface.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final InstitutionRepository institutionRepository;
    private final UserService userService;

    @GetMapping
    public String getAdminPage() {
        return "/admin/home";
    }

    @GetMapping("/institution")
    public String getInstitutionView(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "/admin/institution/list";
    }

    @GetMapping("/institution/add")
    public String getInstitutionAddView(Model model) {
        model.addAttribute("institution", new Institution());
        return "/admin/institution/add";
    }

    @PostMapping("/institution/add")
    public String institutionAdd(@Valid Institution institution, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("institution", institution);
            return "/admin/institution/add";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution";
    }

    @GetMapping("/institution/edit")
    public String getInstitutionEditView(Model model, @RequestParam Long institutionId) {
        model.addAttribute("institution", institutionRepository.getById(institutionId));
        return "/admin/institution/edit";
    }

    @PostMapping("/institution/edit")
    public String institutionEdit(@Valid Institution institution, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("institution", institution);
            return "/admin/institution/edit";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution";
    }

    @GetMapping("/institution/delete")
    public String getInstitutionDeleteView(Model model, @RequestParam Long institutionId) {
        model.addAttribute("institution", institutionRepository.getById(institutionId));
        return "/admin/institution/delete";
    }

    @PostMapping("/institution/delete")
    public String institutionDelete(@RequestParam Long institutionId) {
        institutionRepository.deleteById(institutionId);
        return "redirect:/admin/institution";
    }

    @GetMapping("/admins")
    public String getAdminsListView(Model model) {
        model.addAttribute("admins", userService.findAllByRole("ROLE_ADMIN"));
        return "/admin/admins/list";
    }

    @GetMapping("/admins/add")
    public String getAdminsAddView(Model model) {
        model.addAttribute("users", userService.findAllByRole("ROLE_USER"));
        return "/admin/admins/add";
    }

    @PostMapping("/admins/add")
    public String adminAdd(@RequestParam Long userId) {
        User user = userService.getById(userId);
        user.setRole("ROLE_ADMIN");
        userService.save(user);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admins/edit")
    public String getAdminsEditView(Model model, @RequestParam Long adminId) {
        model.addAttribute("admin", userService.getById(adminId));
        return "/admin/admins/edit";
    }

    @PostMapping("/admins/edit")
    public String adminEdit(@Valid User admin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("admin", admin);
            return "/admin/admins/edit";
        }
        userService.save(admin);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admins/delete")
    public String getAdminsDeleteView(Model model, @RequestParam Long adminId, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User user = userService.getById(adminId);
        if (!authenticatedUser.getUsername().equals(user.getEmail())) {
            model.addAttribute("admin", user);
            return "/admin/admins/delete";
        } else {
            return "redirect:/admin/admins";
        }
    }

    @PostMapping("/admins/delete")
    public String adminDelete(@RequestParam Long adminId, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User user = userService.getById(adminId);
        if (!authenticatedUser.getUsername().equals(user.getEmail())) {
            user.setRole("ROLE_USER");
            userService.save(user);
        }
        return "redirect:/admin/admins";
    }

    @GetMapping("/users")
    public String getUsersListView(Model model) {
        model.addAttribute("users", userService.findAllByRole("ROLE_USER"));
        return "/admin/users/list";
    }

    @GetMapping("/users/edit")
    public String getUsersEditView(Model model, @RequestParam Long userId) {
        model.addAttribute("user", userService.getById(userId));
        return "/admin/users/edit";
    }

    @PostMapping("/users/edit")
    public String userEdit(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "/admin/users/edit";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete")
    public String getUserDeleteView(Model model, @RequestParam Long userId) {
        model.addAttribute("user", userService.getById(userId));
        return "/admin/users/delete";
    }

    @PostMapping("/users/delete")
    public String userDelete(@RequestParam Long userId) {
        userService.deleteById(userId);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/active")
    public String getUserActiveView(Model model, @RequestParam Long userId) {
        model.addAttribute("user", userService.getById(userId));
        return "/admin/users/active";
    }

    @PostMapping("/users/active")
    public String userActive(@RequestParam Long userId) {
        User user = userService.getById(userId);
        user.setActive(!user.getActive());
        userService.save(user);
        return "redirect:/admin/users";
    }

    @ModelAttribute("roles")
    public List<String> roles() {
        return List.of("ROLE_ADMIN", "ROLE_USER");
    }


}
