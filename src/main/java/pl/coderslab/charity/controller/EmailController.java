package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.EmailData;
import pl.coderslab.charity.service_interface.EmailService;
import pl.coderslab.charity.service_interface.MessageService;

import javax.validation.Valid;
import java.util.Locale;

@Controller
//@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final MessageService messageService;
    private final String EMAIL_CONTACT = "dziuba925@gmail.com";

    @PostMapping("/email")
    public String sendEmail(@Valid EmailData email, BindingResult bindingResult, Model model, Locale locale) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(email);
            return "/";
        }
        emailService.sendMessage(EMAIL_CONTACT, messageService.getEmailContactForm(locale), emailService.getMessageFromEmailClass(email, false, locale));
        emailService.sendMessage(email.getEmail(), messageService.getEmailContactForm(locale), emailService.getMessageFromEmailClass(email, true, locale));
        System.out.println("xd");
        model.addAttribute("emailSend", true);
        return "redirect:/";
    }

    @GetMapping("/email/att")
    @ResponseBody
    public String sendAtt() {
        try {
            emailService.sendMessageWithAttachment(EMAIL_CONTACT, "test attachment", "text", "/home/damian/Pulpit/CV Damian Dziuba.pdf");
            return "done";
        } catch (Exception e) {
            return e.toString();
        }

    }
    @ModelAttribute("email")
    public EmailData getEmailData() {
        return new EmailData();
    }


}
