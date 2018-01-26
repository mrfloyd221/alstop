package com.jsonfloyd.alstop.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsonfloyd.alstop.security.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findAccontByEmail(String email);
}
