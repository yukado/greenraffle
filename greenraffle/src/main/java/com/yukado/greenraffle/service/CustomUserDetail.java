package com.yukado.greenraffle.service;

import com.yukado.greenraffle.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CustomUserDetail implements UserDetails {
	
	private User user;
	
	public CustomUserDetail(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(() -> user.getRole());
	}

	public Long getId() { return user.getId(); }


	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	public String getRole() {
		// TODO Auto-generated method stub
		return user.getRole();
	}

	public String getVorname() {
		return user.getVorname();
	}
	public String getNachname() {
		return user.getNachname();
	}
	public LocalDate getGebdatum() {
		return user.getGebdatum();
	}
	public String getCountry() {
		return user.getCountry();
	}
	public String getIban() {
		return user.getIban();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
