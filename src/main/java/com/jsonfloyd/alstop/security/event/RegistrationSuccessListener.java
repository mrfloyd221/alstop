package com.jsonfloyd.alstop.security.event;

import java.util.UUID;

import com.jsonfloyd.alstop.security.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.jsonfloyd.alstop.security.exception.AccountCurrentlyEnabledException;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.service.ITokenService;
import org.springframework.stereotype.Component;

@Component
public class RegistrationSuccessListener implements ApplicationListener<OnRegistrationSuccessEvent> {
	@Autowired
	private ITokenService tokenService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationSuccessEvent event) {
		Account account = event.getAccount();

		VerificationToken token = tokenService.createVerificationToken(account);
		String confirmationUrl = event.getAppUrl() + "/confirm?token=" + token.getToken();
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(account.getEmail());
		email.setSubject("Registration confirmation");
		email.setText("http://localhost:8080" + confirmationUrl);
		mailSender.send(email);
	}

}
