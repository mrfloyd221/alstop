package com.jsonfloyd.alstop.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsonfloyd.alstop.security.model.VerificationToken;
@Repository
public interface VerificationTokenJpaRepository extends JpaRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
	VerificationToken findByAccountId(Long id);
}
