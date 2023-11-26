package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.service_interface.EmailService;

@Service
@RequiredArgsConstructor
public class ScheduledService {
    private final EmailService emailService;

    @Scheduled(cron = "0 0 7 * * ?")
    public void sendDailyEmail() {
        emailService.sendMessage("dziuba925@gmail.com", "run", "Application is running");
    }
}
