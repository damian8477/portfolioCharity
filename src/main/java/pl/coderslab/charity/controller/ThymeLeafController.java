package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.model.EmailData;

@Controller
public class ThymeLeafController {

    @GetMapping("/hello")
    public String helloThymeleaf(Model model) {
        model.addAttribute("messagess", "Hello from Thymeleaf, kurwa nagle dziala!");
       // model.addAttribute("email", new EmailData());
        return "plikpierwszy"; // Odnosi się do pliku hello-thymeleaf.html w katalogu templates
    }

    @ModelAttribute("email")
    public EmailData getEmailData() {
        return new EmailData();
    }
}
