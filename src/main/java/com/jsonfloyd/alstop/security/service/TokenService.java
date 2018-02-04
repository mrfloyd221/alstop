package com.jsonfloyd.alstop.security.service;

import java.util.UUID;

import com.jsonfloyd.alstop.util.service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.jsonfloyd.alstop.security.exception.AccountCurrentlyEnabledException;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.VerificationToken;
import com.jsonfloyd.alstop.security.repository.AccountRepository;
import com.jsonfloyd.alstop.security.repository.VerificationTokenJpaRepository;
@Service
@Primary
public class TokenService implements ITokenService {
	@Autowired
	private VerificationTokenJpaRepository verificationTokenRepository;
	@Autowired
	private TokenGenerator tokenGenerator;
	@Override
	public VerificationToken createVerificationToken(Account account) throws AccountCurrentlyEnabledException{
		String token = tokenGenerator.generateToken(11);
		VerificationToken verificationToken = new VerificationToken(account, token);
		return verificationTokenRepository.save(verificationToken);
	}
	@Override
	public VerificationToken getVerificationToken(String token){
		return verificationTokenRepository.findByToken(token);
	}

}
