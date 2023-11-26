package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.service_interface.MessageService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    @Override
    public String getEmailContact(Locale locale) {
        return messageSource.getMessage("email.contact.message", null, locale);
    }
    @Override
    public String getEmailIncorrect(Locale locale){
        return messageSource.getMessage("email.incorrect", null, locale);
    }
    @Override
    public String getEmailConfirmation(Locale locale){
        return messageSource.getMessage("email.confirmation", null, locale);
    }
    @Override
    public String getPasswordDifferent(Locale locale){
        return messageSource.getMessage("password.different", null, locale);
    }
    @Override
    public String getPasswordRequirements(Locale locale){
        return messageSource.getMessage("password.requirements", null, locale);
    }
    @Override
    public String getPasswordChange(Locale locale){
        return messageSource.getMessage("password.change", null, locale);
    }
    @Override
    public String getEmailContactForm(Locale locale){
        return messageSource.getMessage("email.contact.form", null, locale);
    }
    @Override
    public String getEmailAlreadyTaken(Locale locale){
        return messageSource.getMessage("email.already.taken", null, locale);
    }
    @Override
    public String getRegistrationConfirm(Locale locale){
        return messageSource.getMessage("registration.confirm", null, locale);
    }
    @Override
    public String getEmailContactMessageAdmin(Locale locale){
        return messageSource.getMessage("email.contact.message.admin", null, locale);
    }

}
