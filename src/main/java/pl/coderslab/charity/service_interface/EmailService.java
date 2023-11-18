package pl.coderslab.charity.service_interface;

import pl.coderslab.charity.model.Email;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessage(String to, String subject, String text);

    void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException;

    String getMessageFromEmailClass(Email email, boolean toUser);
}
