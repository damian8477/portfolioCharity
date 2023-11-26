package pl.coderslab.charity.service_interface;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.EmailData;

import javax.mail.MessagingException;
import java.util.Locale;

public interface EmailService {
    void sendMessage(String to, String subject, String text);

    void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException;

    String getMessageFromEmailClass(EmailData email, boolean toUser, Locale locale);

    String getConfirmationMessage(User user);

    String getChangePasswordMessage(User user);
}
