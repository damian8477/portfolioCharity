package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service_interface.EmailService;
import pl.coderslab.charity.service_interface.TokenService;
import pl.coderslab.charity.service_interface.UserService;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

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
    public String remindPassword(User user, BindingResult bindingResult) {
        if (!userService.existsByEmail(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", "Podany email jest nie poprawny"));
            return "/remind-password";
        }
        user = userService.getByEmail(user.getEmail());
        if (!user.getActive()) {
            bindingResult.addError(new FieldError("user", "email", "Potwierdź aktywacje konta, link wysłany został na adres email"));
            return "/remind-password";
        }
        emailService.sendMessage(user.getEmail(), "Link do zmiany hasła", emailService.getChangePasswordMessage(user));
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
    public String remindPage(@Valid User user, BindingResult bindingResult, @RequestParam String password2, Model model) {
        if (bindingResult.hasErrors() || !user.getPassword().equals(password2) || !user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            if (!user.getPassword().equals(password2)) {
                bindingResult.addError(new FieldError("user", "password", "Hasła się różnią"));
            }
            if (!user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                bindingResult.addError(new FieldError("user", "password", "Hasło musi zawierać: wielkie litery, małe litery, cyfry i znaki specjalne"));
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
}
