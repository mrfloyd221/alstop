package com.jsonfloyd.alstop.security.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	private AccountRepository accountRepository;
	@Override
	public VerificationToken createVerificationToken(Account account, String token) throws AccountCurrentlyEnabledException{
	
		VerificationToken verificationToken = new VerificationToken(account, token);
		return verificationTokenRepository.save(verificationToken);
	}
	@Override
	public VerificationToken getVerificationToken(String token){
		return verificationTokenRepository.findByToken(token);
	}
	@Override
	public VerificationToken getVerificationTokenByEmail(String email)throws UsernameNotFoundException{
		Account acc = accountRepository.findAccontByEmail(email);
		if(acc == null)
			throw new UsernameNotFoundException("User not found with email = " + email);
		return verificationTokenRepository.findByAccountId(acc.getId());
	}
}
