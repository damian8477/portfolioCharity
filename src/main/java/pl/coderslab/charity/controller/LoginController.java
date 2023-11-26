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
import pl.coderslab.charity.service.MessageServiceImpl;
import pl.coderslab.charity.service_interface.EmailService;
import pl.coderslab.charity.service_interface.MessageService;
import pl.coderslab.charity.service_interface.TokenService;
import pl.coderslab.charity.service_interface.UserService;

import javax.validation.Valid;

import java.util.Locale;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;

    @GetMapping
    public String getLoginView(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/remind-pass")
    public String getRemindPassword(Model model) {
        model.addAttribute("user", new User());
        return "remind-password";
    }

    @PostMapping("/remind-pass")
    public String remindPassword(User user, BindingResult bindingResult, Locale locale) {
        if (!userService.existsByEmail(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", messageService.getEmailIncorrect(locale)));
            return "/remind-password";
        }
        user = userService.getByEmail(user.getEmail());
        if (!user.getActive()) {
            bindingResult.addError(new FieldError("user", "email", messageService.getEmailConfirmation(locale)));
            return "/remind-password";
        }
        emailService.sendMessage(user.getEmail(), messageService.getPasswordChange(locale), emailService.getChangePasswordMessage(user));
        return "redirect:/";
    }

    @GetMapping("/remind-page")
    public String getRemindPage(@RequestParam String token, Model model) {
        Token tokenDb = tokenService.getByToken(token);
        if (!isNull(tokenDb)) {
            if (tokenDb.getToken().equals(token)) {
                User user = tokenDb.getUser();
                user.setPassword("");
                model.addAttribute("user", tokenDb.getUser());
                return "remind-page";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/remind-page")
    public String remindPage(@Valid User user, BindingResult bindingResult, @RequestParam String password2, Model model, Locale locale) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(password2) || !user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            if (!user.getPassword().equals(password2)) {
                bindingResult.addError(new FieldError("user", "password", messageService.getPasswordDifferent(locale)));
            }
            if (!user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                bindingResult.addError(new FieldError("user", "password", messageService.getPasswordRequirements(locale)));
            }
            model.addAttribute("user", user);
            return "remind-page";
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userService.save(user);
        tokenService.deleteAllByUserId(user.getId());
        return "redirect:/login";
    }
    @ModelAttribute("email")
    public EmailData getEmailData() {
        return new EmailData();
    }
}
