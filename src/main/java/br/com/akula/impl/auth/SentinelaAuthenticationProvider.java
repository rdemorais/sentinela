package br.com.akula.impl.auth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.akula.api.ca.UserDetails;
import br.com.akula.api.dao.SentinelaDao;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;

/**
 * Classe utilit&aacute;ria para {@link AuthenticationProvider}. Suporta auth do tipo username e password.
 * <br>
 * Inclui no conjunto de {@link Permissao} a ROLE_AUTENTICADO
 * 
 * @author Rafael de Morais
 *
 */
public class SentinelaAuthenticationProvider implements AuthenticationProvider{

	private static final Logger logger = LoggerFactory.getLogger(SentinelaAuthenticationProvider.class);
	
	private final PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	private SentinelaDao sentinelaDao;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		logger.debug("Iniciando autenticacao para usuario");
		try {
			String login = String.valueOf(auth.getPrincipal());
			String senha = String.valueOf(auth.getCredentials());
			
			String loginSenha = login + senha;
			
			logger.debug("Capturando dados do usuario na base de dados ["+login+"]");
			Usuario user = sentinelaDao.findUsuario(login);
			
			if(user != null && encoder.matches(loginSenha, user.getSenha())) {
				return configAuth(user);
			}else {
				Long idFacebook = Long.valueOf(String.valueOf(auth.getPrincipal()));
				
				logger.debug("Autenticacao user/pass falhou. Tentando encontrar pelo idFacebook [" + idFacebook + "]");
				
				user = sentinelaDao.findUsuarioByIDFacebook(idFacebook);
				
				if(user != null) {
					return configAuth(user);
				}
				
				throw new BadCredentialsException("Autenticacao falhou. Usuario ou senha nao validados");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	private Autenticacao configAuth(Usuario user) {
		Map<UserDetails, Object> userDet = new HashMap<UserDetails, Object>();
		Autenticacao autenticacao = new Autenticacao();
		autenticacao.setName(user.getLogin());
		autenticacao.setAuthenticated(true);
		autenticacao.addPermissao(new PermissaoConcedida("ROLE_AUTENTICADO"));
		
		userDet.put(UserDetails.ID_USER, user.getId());
		
		autenticacao.setDetails(userDet);
		
		logger.debug("Autenticacao ok para usuario ["+user.getLogin()+"]");
		return autenticacao;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}