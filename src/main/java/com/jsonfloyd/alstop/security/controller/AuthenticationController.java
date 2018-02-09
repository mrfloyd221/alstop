package com.jsonfloyd.alstop.security.controller;

import com.jsonfloyd.alstop.security.exception.TokenNotFoundException;
import com.jsonfloyd.alstop.util.exception.ResourceException;
import com.jsonfloyd.alstop.util.model.GenericResponse;
import com.jsonfloyd.alstop.util.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.AccountDto;
import com.jsonfloyd.alstop.security.model.VerificationToken;
import com.jsonfloyd.alstop.security.service.AccountService;
import com.jsonfloyd.alstop.security.service.TokenService;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@Slf4j
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
    @ResponseStatus(HttpStatus.CREATED)
	public GenericResponse signUp(@RequestBody AccountDto acc){
		if(acc == null)
			throw new BadCredentialsException("account cannot be null");
		//TODO validate credentials
		Account account =  accountService.createAccount(acc);
        VerificationToken token = tokenService.createVerificationToken(account);
        messageService.sendActivationToken(token.getToken(), account.getEmail());
		GenericResponse response = new GenericResponse();
        response.setHttpStatus(HttpStatus.CREATED);
		response.addField("message", "account successfully created");
		response.addField("location", "localhost:8080/user/"+account.getId());
		response.addField("account", account);
	    return response;
	}
	@RequestMapping(
			value = "/confirm",
			params = { "token"},
			method = RequestMethod.GET)
	@ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
	public GenericResponse confirmEmail(@RequestParam("token") String token){
		VerificationToken verificationToken = tokenService.getVerificationToken(token);
		if(verificationToken == null)
			throw new ResourceException("Verification token cannot be null");
		if(verificationToken.isExpired())
            throw new ResourceException("Verification token expired");
		//TODO account enabled event
		Account account = verificationToken.getAccount();
		account = accountService.enableAccount(account.getId());
        GenericResponse response = new GenericResponse();
        response.addField("message", "account " + account.getEmail() + " activated");
        response.setHttpStatus(HttpStatus.ACCEPTED);
        response.addField("account", account.getId());
		return response;
	}
	@RequestMapping(
			value = "/reactivate",
			params = { "token"},
			method = RequestMethod.GET)
	@ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
	public GenericResponse resendConfirmationToken(@RequestParam("token") String oldToken) throws TokenNotFoundException{
        VerificationToken token = tokenService.getVerificationToken(oldToken);
        if(token == null)
            throw new TokenNotFoundException("Token " + oldToken + " not found");
        VerificationToken newToken = tokenService.generateNewVerificationToken(token);
        messageService.sendActivationToken(newToken.getToken(),newToken.getAccount().getEmail());
		return GenericResponse.createSimpleMessageResponse("Token sent to " + newToken.getAccount().getEmail(), HttpStatus.ACCEPTED);
	}
	
}
