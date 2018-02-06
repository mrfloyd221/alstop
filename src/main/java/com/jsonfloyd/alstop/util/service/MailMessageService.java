package com.jsonfloyd.alstop.util.service;

import com.jsonfloyd.alstop.security.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailMessageService implements MessageService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private MailSender mailSender;
    @Override
    public void sendActivationToken(String token, String email) {
        String confirmationUrl = "/confirm?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registration confirmation");
        message.setText("http://localhost:8080" + confirmationUrl);
        mailSender.send(message);
    }

    @Override
    public void sendRegistrationGratitude(String email, Account account) {

    }
}
