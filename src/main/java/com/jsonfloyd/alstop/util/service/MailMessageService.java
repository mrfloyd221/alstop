package com.jsonfloyd.alstop.util.service;

import com.jsonfloyd.alstop.security.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailMessageService implements MessageService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Override
    public void sendActivationToken(String token, String email) throws MessagingException {
        Context ctx = new Context();
        ctx.setVariable("token", token);
        String text = templateEngine.process("mail/activation", ctx);
        this.sendMessage(email, "Confirm your email", text);
    }
    @Override
    public void sendRegistrationGratitude(Account account)throws MessagingException {
        Context ctx = new Context();
        ctx.setVariable("account", account);
        String text = templateEngine.process("mail/registration-gratitude", ctx);
        this.sendMessage(account.getEmail(), "Registration gratitude", text);
    }
    private void sendMessage(String to, String subject, String text)throws MessagingException {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(mimeMailMessage);
    }
}
