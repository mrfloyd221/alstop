package com.jsonfloyd.alstop.security.service;

import com.jsonfloyd.alstop.security.exception.AccountCurrentlyEnabledException;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.VerificationToken;

public interface ITokenService {
	VerificationToken createVerificationToken(Account account, String token) throws AccountCurrentlyEnabledException;
	VerificationToken getVerificationToken(String token);
	VerificationToken getVerificationTokenByEmail(String email);
}
