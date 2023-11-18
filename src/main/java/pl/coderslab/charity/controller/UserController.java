package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/edit")
    public String getUserEditView(Model model, @AuthenticationPrincipal UserDetails authenticatedUser){
        model.addAttribute("user", userRepository.getByEmail(authenticatedUser.getUsername()));
        return "/user/edit";
    }

    @PostMapping("/edit")
    public String userEdit(@Valid User user, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails authenticatedUser){
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "/user/edit";
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "redirect:/";
    }
}
