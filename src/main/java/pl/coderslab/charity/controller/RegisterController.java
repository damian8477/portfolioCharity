package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.EmailData;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service_interface.EmailService;
import pl.coderslab.charity.service_interface.MessageService;
import pl.coderslab.charity.service_interface.TokenService;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final MessageService messageService;

    @GetMapping
    public String getRegisterView(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String processRegistrationPage2(@Valid User user, BindingResult bindingResult, @RequestParam String password2, Model model, Locale locale) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(password2) || !user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            if (!user.getPassword().equals(password2)) {
                bindingResult.addError(new FieldError("user", "password", messageService.getPasswordDifferent(locale)));
            }
            if (!user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                bindingResult.addError(new FieldError("user", "password", messageService.getPasswordRequirements(locale)));
            }
            model.addAttribute("user", user);
            return "register";
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", messageService.getEmailAlreadyTaken(locale)));
            model.addAttribute("user", user);
            return "register";
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setActive(false);
        user.setRole("ROLE_USER");
        user = userRepository.save(user);
        emailService.sendMessage(user.getEmail(), messageService.getRegistrationConfirm(locale), emailService.getConfirmationMessage(user));
        return "redirect:/login";
    }

    @GetMapping("/confirm")
    public String confirmAccount(@RequestParam String token) {
        Token tok = tokenService.getByToken(token);
        User user = tok.getUser();
        user.setActive(true);
        tokenService.deleteById(tok.getId());
        return "redirect:/login";
    }
    @ModelAttribute("email")
    public EmailData getEmailData() {
        return new EmailData();
    }

}
