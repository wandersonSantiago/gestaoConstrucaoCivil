package br.com.system.gestaoConstrucaoCivil.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl  implements GrantedAuthority{

private static final long serialVersionUID = 1L;
	
	
	private String name;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
