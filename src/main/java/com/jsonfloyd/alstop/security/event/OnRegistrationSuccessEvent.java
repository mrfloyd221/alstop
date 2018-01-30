package com.jsonfloyd.alstop.security.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.jsonfloyd.alstop.security.model.Account;

import lombok.Getter;
import lombok.Setter;

public class OnRegistrationSuccessEvent extends ApplicationEvent {
	private static final long serialVersionUID = 6641274161559750304L;
	@Getter @Setter private Account account;
	@Getter @Setter private String appUrl;
	@Getter @Setter private Locale locale;
	public OnRegistrationSuccessEvent(Account acc, String appUrl, Locale locale){
		super(acc);
		this.account = acc;
		this.appUrl = appUrl;
		this.locale = locale;
	}
}
