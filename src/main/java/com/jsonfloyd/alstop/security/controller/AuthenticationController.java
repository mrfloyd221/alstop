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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsonfloyd.alstop.security.config.SecurityProperties;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.AccountDto;
import com.jsonfloyd.alstop.security.service.AccountService;

@Controller
public class AuthenticationController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	@ResponseBody
	public Account signUp(@RequestBody AccountDto acc){
		return accountService.createAccount(acc);
	}
}
