package br.com.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private List<GrantedAuthorityImpl> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.authorities;
	}

	public void addAuthority(String authorityName) {
		if (this.authorities == null) {
			this.authorities = new ArrayList<>();
		}
		GrantedAuthorityImpl aut = new GrantedAuthorityImpl();
		aut.setName(authorityName);
		authorities.add(aut);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<GrantedAuthorityImpl> authorities) {
		this.authorities = authorities;
	}
}
