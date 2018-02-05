package com.jsonfloyd.alstop.security.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import com.jsonfloyd.alstop.security.exception.AccountCurrentlyEnabledException;

import lombok.Getter;
import lombok.Setter;
@Entity
public class VerificationToken {
	private static final int EXPIRATION = 60 * 24;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Long id;

	@Getter @Setter private String token;

	@OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "account_id")
	@Getter @Setter private Account account;

	@Getter @Setter private Date expiryDate;
	public VerificationToken(){
		super();
	}
	public VerificationToken(Account account, String token) throws AccountCurrentlyEnabledException{
		if(account.isEnabled())
			throw new AccountCurrentlyEnabledException("Accaount with email " + account.getEmail() + "currently enabled");
		this.account = account;
		this.expiryDate = this.calculateExpiryDate(EXPIRATION);
		this.token = token;
	}
	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	public boolean isExpired(){
		Calendar cal = Calendar.getInstance();
		return cal.before(this.expiryDate);
	}
	
}
