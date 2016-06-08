package br.com.akula.impl.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.akula.api.ca.NavBarItem;
import br.com.akula.api.ca.UserDetails;
import br.com.akula.api.model.Pagina;

public class SentinelaServiceImpl extends AbstractSentinelaService {
	
	@Override
	@SuppressWarnings("unchecked")
	public Serializable usuarioLogadoId() throws RuntimeException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<UserDetails, Object> userDet = (Map<UserDetails, Object>) auth.getDetails();
		return (Serializable) userDet.get(UserDetails.ID_USER);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<NavBarItem> usuarioNavBar() throws RuntimeException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<UserDetails, Object> userDet = (Map<UserDetails, Object>) auth.getDetails();
		
		return (List<NavBarItem>) userDet.get(UserDetails.NAVBAR);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void atualizaUsuarioPaginaPadrao(String novaPagina) throws RuntimeException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<UserDetails, Object> userDet = (Map<UserDetails, Object>) auth.getDetails();
		Pagina pag = sentinelaDao.findPagina(novaPagina);
		userDet.remove(UserDetails.PAGINA_PADRAO);
		userDet.put(UserDetails.PAGINA_PADRAO, pag.getURL());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String usuarioPaginaPadrao() throws RuntimeException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<UserDetails, Object> userDet = (Map<UserDetails, Object>) auth.getDetails();
		String paginaPadrao = (String)userDet.get(UserDetails.PAGINA_PADRAO);
		return paginaPadrao;
	}
}