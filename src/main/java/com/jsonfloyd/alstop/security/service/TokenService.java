package com.jsonfloyd.alstop.security.service;

import com.jsonfloyd.alstop.security.exception.AccountCurrentlyEnabledException;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.VerificationToken;

public interface TokenService {
	VerificationToken createVerificationToken(Account account) throws AccountCurrentlyEnabledException;
	VerificationToken getVerificationToken(String token);
	VerificationToken generateNewVerificationToken(VerificationToken oldToken);

}
