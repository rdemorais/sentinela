package br.com.akula;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration("/META-INF/sentinela-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SentinelaSecurityTest {

	private static final Logger logger = LoggerFactory.getLogger(SentinelaSecurityTest.class);

	//private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(this.springSecurityFilterChain).build();
		logger.debug("MockMvc criado com sucesso");
	}

	@Test
	public void testSentinelaAuthenticationProvider() throws Exception {
		mockMvc.perform(post("/j_spring_security_check").param("j_username", "bill").param("j_password", "abc123"))
				.andExpect(new ResultMatcher() {
					public void match(MvcResult mvcResult) throws Exception {
						logger.debug("testSentinelaAuthenticationProvider ok...");
					}
				});
	}
}