package br.com.system.gestaoConstrucaoCivil.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {

		String senha = rawPassword.toString();
		int i, j, k;
		String s = "";
		Character saux;

		while (senha.length() < 50) {
			senha += "S";
		}
		for (i = 0; i < 50; i++) {
			k = 1;
			for (j = 0; j < 50; j++) {
				k = k + senha.charAt(j) * (i + 1) * (j + 1) * (k % 11);
			}
			saux = (char) (k % 255);
			while (!((saux >= 97 && saux <= 122) || (saux >= 65 && saux <= 90) || (saux >= 49 && saux <= 57))) {
				if ((saux + 57) >= 256) {
					saux = (char) ((saux + 57) % 255);
				} else {
					saux = (char) (saux + 57);
				}
			}
			s += saux;
		}
		return s;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		if (encodedPassword == null || encodedPassword.length() == 0) {
			
			return false;
		}
		if (rawPassword == null || rawPassword.length() == 0) {
			
			return false;
		}
		return encode(rawPassword).equals(encodedPassword);
	}
}
