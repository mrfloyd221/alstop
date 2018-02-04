package com.jsonfloyd.alstop.security.model;

import lombok.Getter;
import lombok.Setter;

public class AccountDto {
	//TODO fields validation
	@Getter @Setter private String email;
	@Getter @Setter private String password;
}
