package com.jsonfloyd.alstop.security.service;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsonfloyd.alstop.security.model.Account;
import com.jsonfloyd.alstop.security.model.AccountDto;
import com.jsonfloyd.alstop.security.repository.AccountRepository;
@Primary
@Service
public class AccountService implements UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Account acc = accountRepository.findAccontByEmail(arg0);
		if(acc == null)
			throw new UsernameNotFoundException("User not found with username = " + arg0);
		return acc;
	}
	public Account createAccount(AccountDto acc){
		Account user = new Account();
		user.setEmail(acc.getEmail());
		user.setPassword(passwordEncoder.encode(acc.getPassword()));
		user.grantAuthority("USER");
		return accountRepository.save(user);
		
	}
	
}
