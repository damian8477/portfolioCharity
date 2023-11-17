package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String getLoginView(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}
