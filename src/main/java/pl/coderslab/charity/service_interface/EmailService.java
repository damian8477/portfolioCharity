package pl.coderslab.charity.service_interface;

import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.EmailData;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessage(String to, String subject, String text);

    void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException;

    String getMessageFromEmailClass(EmailData email, boolean toUser);

    String getConfirmationMessage(User user);

    String getChangePasswordMessage(User user);
}
