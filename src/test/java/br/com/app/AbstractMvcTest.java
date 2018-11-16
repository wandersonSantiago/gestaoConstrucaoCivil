/*package br.com.app;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@Ignore
public class AbstractMvcTest {

	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	protected MockHttpSession session;
	
	@Before
	public void setup() {
		this.session = new MockHttpSession();
		mockMvc = webAppContextSetup(webApplicationContext)
				.apply(springSecurity())
				.alwaysDo(print())
				.build();
		 setAuthentication("root", "123", session);
	}
	protected void setAuthentication(String user, String password,MockHttpSession session) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, password);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);

		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
	}
}
*/