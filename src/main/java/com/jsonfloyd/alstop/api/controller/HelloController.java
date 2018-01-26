package com.jsonfloyd.alstop.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return "hello "+ name;
	}
}
