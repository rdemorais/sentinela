package br.com.akula;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/META-INF/sentinela-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordEncoderTest {
	
	private final PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void gerarHashTest() {
		System.out.println(encoder.encode("123"));
	}
}