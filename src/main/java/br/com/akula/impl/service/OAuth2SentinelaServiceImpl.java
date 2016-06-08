package br.com.akula.impl.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import br.com.akula.api.ca.NavBarItem;
import br.com.akula.api.ca.UserDetails;

public class OAuth2SentinelaServiceImpl extends AbstractSentinelaService{

	@Override
	public String usuarioPaginaPadrao() throws RuntimeException {
		return null;
	}

	@Override
	public void atualizaUsuarioPaginaPadrao(String novaPagina) throws RuntimeException {
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Serializable usuarioLogadoId() throws RuntimeException {
		Authentication auth = ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication()).getUserAuthentication();
		
		Map<UserDetails, Object> userDet = (Map<UserDetails, Object>) auth.getDetails();
		return (Serializable) userDet.get(UserDetails.ID_USER);
	}

	@Override
	public List<NavBarItem> usuarioNavBar() throws RuntimeException {
		return null;
	}
}