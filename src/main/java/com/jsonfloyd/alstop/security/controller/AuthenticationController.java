package com.jsonfloyd.alstop.security.controller;

import com.jsonfloyd.alstop.util.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.AccountDto;
import com.jsonfloyd.alstop.security.model.VerificationToken;
import com.jsonfloyd.alstop.security.service.AccountService;
import com.jsonfloyd.alstop.security.service.TokenService;

@Controller
public class AuthenticationController {
	private final AccountService accountService;
	private final TokenService tokenService;
    private final MessageService messageService;
	@Autowired
	public AuthenticationController(AccountService accountService, TokenService tokenService, MessageService messageService) {
		this.accountService = accountService;
		this.tokenService = tokenService;
        this.messageService = messageService;
    }

	@PostMapping("/register")
	@ResponseBody
	public Account signUp(@RequestBody AccountDto acc){
		//TODO create activation token
		if(acc == null)
			//TODO bad credentials error
			return null;
		//TODO validate credentials
		Account account =  accountService.createAccount(acc);
		if(account == null)
		    //TODO account not created
		    return null;
        VerificationToken token = tokenService.createVerificationToken(account);
        messageService.sendActivationToken(token.getToken(), account.getEmail());
		//TODO CREATED response
		return account;
	}
	@RequestMapping(
			value = "/confirm",
			params = { "token"},
			method = RequestMethod.GET)
	@ResponseBody
	public String confirmEmail(@RequestParam("token") String token){
		VerificationToken verificationToken = tokenService.getVerificationToken(token);
		if(verificationToken == null)
			//TODO token not exist error
			return "token not found";
		if(verificationToken.isExpired())
			//TODO expired error
			return "expired";
		//TODO account enabled event
		Account account = verificationToken.getAccount();
		if(account == null)
			return "account is null";
		account = accountService.enableAccount(account.getId());
		//TODO success confirmation response
		return "account " + account.getEmail() + " activated";
	}
	@RequestMapping(
			value = "/reactivate",
			params = { "token"},
			method = RequestMethod.GET)
	@ResponseBody
	public String resendConfirmationToken(@RequestParam("token") String oldToken){
		//TODO resent activation
        VerificationToken token = tokenService.getVerificationToken(oldToken);
        if(token == null)
            return "token not found";
        VerificationToken newToken = tokenService.generateNewVerificationToken(token);
        messageService.sendActivationToken(newToken.getToken(),newToken.getAccount().getEmail());
        //TODO success verification token resend
		return "sent";
	}
	
}
