package com.jsonfloyd.alstop.security.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.jsonfloyd.alstop.security.exception.AccountCurrentlyEnabledException;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.service.ITokenService;

public class RegistrationSucessListener implements ApplicationListener<OnRegistrationSuccessEvent> {
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private ITokenService tokenService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationSuccessEvent event) {
		// TODO Auto-generated method stub
		Account account = event.getAccount();
		String token = UUID.randomUUID().toString();

		tokenService.createVerificationToken(account, token);
		String confirmationUrl = event.getAppUrl() + "/confirm&token=" + token;
		String message = messageSource.getMessage("messages.regSuccess", null, event.getLocale());
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(account.getEmail());
		email.setSubject("Registration confirmation");
		email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
		mailSender.send(email);

	}

}
