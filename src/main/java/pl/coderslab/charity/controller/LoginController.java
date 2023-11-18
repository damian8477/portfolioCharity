package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service_interface.EmailService;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;
    private final EmailService emailService;
    @GetMapping
    public String getLoginView(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/remind-pass")
    public String getRemindPassword(Model model){
        model.addAttribute("user", new User());
        return "remind-password";
    }

    @PostMapping("/remind-pass")
    public String remindPassword(User user, BindingResult bindingResult, Model model){
        if(!userRepository.existsByEmail(user.getEmail())){
            bindingResult.addError(new FieldError("user", "email", "Podany email jest nie poprawny"));
            return "/remind-password";
        }
        user = userRepository.getByEmail(user.getEmail());
        if(!user.getActive()){
            bindingResult.addError(new FieldError("user", "email", "Potwierdź aktywacje konta, link wysłany został na adres email"));
            return "/remind-password";
        }
        emailService.sendMessage(user.getEmail(), "Link do zmiany hasła", emailService.getChangePasswordMessage(user));
        return "redirect:/";
    }
}
