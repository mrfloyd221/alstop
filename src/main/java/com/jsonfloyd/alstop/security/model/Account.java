package com.jsonfloyd.alstop.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
@Entity
public class  Account implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4839579351277661742L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@Getter @Setter private String email;
	@JsonIgnore
	@Setter private String password;
	@ElementCollection(fetch= FetchType.EAGER)
	@Setter private List<String> roles = new ArrayList<String>();
	@JsonIgnore
	@Setter private boolean enabled, credentialNonExpired, accountNonExpired, accountNonLocked;
	
	public Account(){
		this.enabled = false;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialNonExpired = true;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));
		});
		return authorities;
	}
	public void grantAuthority(String role){
		roles.add(role);
	}
	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {

		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return this.credentialNonExpired;
	}

	@Override
	public boolean isEnabled() {

		return this.enabled;
	}

}
