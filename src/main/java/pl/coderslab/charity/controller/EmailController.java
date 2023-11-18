package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.model.Email;
import pl.coderslab.charity.service_interface.EmailService;

import javax.validation.Valid;

@Controller
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final String EMAIL_CONTACT = "dziuba925@gmail.com";
    @PostMapping
    public String sendEmail(@Valid Email email, BindingResult bindingResult,  Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute(email);
            return "/";
        }
        emailService.sendMessage(EMAIL_CONTACT, "user contanct", emailService.getMessageFromEmailClass(email, false));
        emailService.sendMessage(email.getEmail(), "Formularz kontaktowy", emailService.getMessageFromEmailClass(email, true));
        model.addAttribute("emailSend", true);
        return "redirect:/";
    }

    @GetMapping("/att")
    @ResponseBody
    public String sendAtt(){
        try{
            emailService.sendMessageWithAttachment("dziuba925@gmail.com", "xd", "text", "/home/damian/Pulpit/CV Damian Dziuba.pdf");
            return "done";
        }catch (Exception e){
            return e.toString();
        }

    }


}
