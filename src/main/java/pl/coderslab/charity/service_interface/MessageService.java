package pl.coderslab.charity.service_interface;

import java.util.Locale;

public interface MessageService {
    String getEmailContact(Locale locale);

    String getEmailIncorrect(Locale locale);

    String getEmailConfirmation(Locale locale);

    String getPasswordDifferent(Locale locale);

    String getPasswordRequirements(Locale locale);

    String getPasswordChange(Locale locale);

    String getEmailContactForm(Locale locale);

    String getEmailAlreadyTaken(Locale locale);

    String getRegistrationConfirm(Locale locale);

    String getEmailContactMessageAdmin(Locale locale);
}
