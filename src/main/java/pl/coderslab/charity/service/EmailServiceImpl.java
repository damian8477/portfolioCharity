package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.EmailData;
import pl.coderslab.charity.service_interface.EmailService;
import pl.coderslab.charity.service_interface.TokenService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TokenService tokenService;

    private String USER_MESSAGE = "Otrzymaliśmy Twoją wiadomość o treści:\n %s \n\n Odpowiemy na nią najszybciej jak będzie to możliwe. \n Pozdrawiamy \n Administratorzy";

    @Override
    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("file.pdf", file);
        emailSender.send(message);
    }

    @Override
    public String getMessageFromEmailClass(EmailData email, boolean toUser) {
        if (toUser) {
            return String.format(USER_MESSAGE, getMessageForAdmin(email));
        } else {
            return getMessageForAdmin(email);
        }
    }

    private String getMessageForAdmin(EmailData email) {
        return String.format("%s \nImię: %s\nNazwisko: %s\nEmail: %s\n\n Wiadomość: %s", LocalDateTime.now(), email.getName(), email.getSurname(), email.getEmail(), email.getMessage());
    }

    @Override
    public String getConfirmationMessage(User user) {
        String code = generateCode(user);
        return String.format("http://localhost:8081/register/confirm?token=%s", code);
    }

    @Override
    public String getChangePasswordMessage(User user) {
        String code = generateCode(user);
        return String.format("http://localhost:8081/login/remind-page?token=%s", code);
    }

    private String generateCode(User user) {
        Token token = new Token();
        String code = UUID.randomUUID().toString();
        token.setToken(code);
        token.setUser(user);
        tokenService.save(token);
        return code;
    }
}