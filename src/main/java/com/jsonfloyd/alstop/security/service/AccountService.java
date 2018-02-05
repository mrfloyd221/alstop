package com.jsonfloyd.alstop.security.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsonfloyd.alstop.security.exception.AccountCurrentlyEnabledException;
import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.AccountDto;
import com.jsonfloyd.alstop.security.model.VerificationToken;
import com.jsonfloyd.alstop.security.repository.AccountRepository;
import com.jsonfloyd.alstop.security.repository.VerificationTokenJpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

@Primary
@Service
public class AccountService implements  UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Account acc = accountRepository.findAccontByEmail(arg0);
		if(acc == null)
			throw new UsernameNotFoundException("User not found with email = " + arg0);
		return acc;
	}
	public Account createAccount(AccountDto acc){
		Account user = new Account();
		user.setEmail(acc.getEmail());
		user.setPassword(passwordEncoder.encode(acc.getPassword()));
		user.grantAuthority("USER");
		return accountRepository.save(user);
		
	}
	public Account enableAccount(Long accountId){
		Account existedAccount = accountRepository.findOne(accountId);
		existedAccount.setEnabled(true);
		accountRepository.save(existedAccount);
		return existedAccount;

	}

	
}
