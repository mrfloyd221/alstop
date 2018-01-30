package com.jsonfloyd.alstop.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsonfloyd.alstop.security.config.SecurityProperties;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.AccountDto;
import com.jsonfloyd.alstop.security.model.VerificationToken;
import com.jsonfloyd.alstop.security.service.AccountService;
import com.jsonfloyd.alstop.security.service.ITokenService;

@Controller
public class AuthenticationController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private ITokenService tokenService;
	@PostMapping("/register")
	@ResponseBody
	public Account signUp(@RequestBody AccountDto acc){
		//TODO create activation token
		return accountService.createAccount(acc);
	}
	@GetMapping
	@ResponseBody
	public String confirmEmail(@RequestParam("token") String token){
		VerificationToken verificationToken = tokenService.getVerificationToken(token);
		if(verificationToken == null)
			//TODO token not exist error
			return null;
		if(verificationToken.isExpired())
			//TODO expired error
			return null;
		//TODO account enabled event
		Account account = verificationToken.getAccount();
		account.setEnabled(true);
		//TODO success confirmation response
		return "success";
	}
	@GetMapping
	@ResponseBody
	public String resendConfirmationToken(@RequestParam("token") String oldToken){
		//TODO resent activation
		return null;
	}
	
}
