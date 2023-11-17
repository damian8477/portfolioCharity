package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @GetMapping
    public String getRegisterView(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String processRegistrationPage2(@Valid User user, @RequestParam String password2, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors() || !user.getPassword().equals(password2)){
            if(!user.getPassword().equals(password2)){
                bindingResult.addError(new FieldError("user", "password", "Hasła się różnią"));
            }
            model.addAttribute("user", user);
            return "registration";
        }
        if(userRepository.existsByEmail(user.getEmail())){
            bindingResult.addError(new FieldError("user", "email", "Podany email jest już zajęty"));
            model.addAttribute("user", user);
            return "registration";
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return "redirect:/login";
    }
}
