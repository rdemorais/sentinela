package br.com.akula.impl.auth;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.akula.api.ca.NavBarItem;
import br.com.akula.api.ca.UserDetails;
import br.com.akula.api.dao.SentinelaDao;
import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;
import br.com.akula.impl.ca.NavBarItemImpl;

public abstract class SentinelaAuthenticationSuccessHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(SentinelaAuthenticationSuccessHelper.class);
	
	@Autowired
	protected SentinelaDao sentinelaDao;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void configuraNavbar(Autenticacao autenticacao)
			throws IOException, ServletException {
		logger.debug("Configurando NavBar");
		
		Usuario user = sentinelaDao.findUsuario(autenticacao.getName());
		
		Map<UserDetails, Object> userDet = (Map<UserDetails, Object>) autenticacao.getDetails();
		
		List<NavBarItem> navBarItens = new ArrayList<NavBarItem>();
		
		List<Permissao> permissoes = sentinelaDao.listPermissaoUsuario(user.getId());
		List<Pagina> paginas = sentinelaDao.listPaginas(user.getId(), null, false);
		
		for (Permissao permissao : permissoes) {
			autenticacao.addPermissao(new PermissaoConcedida(permissao.getNome()));
		}
		
		navBarConfig(user.getId(), paginas, navBarItens);
		String paginaPadraoUrl = "";
		if(user.getPaginaPadrao() != null) {
			paginaPadraoUrl = user.getPaginaPadrao().getURL();
		}else {
			paginaPadraoUrl = "";
		}
		userDet.put(UserDetails.PAGINA_PADRAO, paginaPadraoUrl);
		userDet.put(UserDetails.NAVBAR, navBarItens);
		userDet.put(UserDetails.ID_USER, user.getId());
	}
	
	private void navBarConfig(Serializable userId, List<Pagina> paginas, List<NavBarItem> navBarItens) {
		NavBarItem navPai;
		for (Pagina pag : paginas) {
			navPai = navBarItemConfig(userId, pag, navBarItens);
			//Configura paginas filhas
			_navBarConfig(userId, navPai, pag, navBarItens);
		}
	}
	
	private void _navBarConfig(Serializable userId, NavBarItem navPai, Pagina pagina, List<NavBarItem> navBarItens) {
		List<Pagina> paginasFilhas = sentinelaDao.listPaginas(userId, pagina, false);
		NavBarItem navFilha;
		for (Pagina pFilha : paginasFilhas) {
			navFilha = navBarItemConfig(userId, pFilha, navBarItens);
			navPai.addItem(navFilha);
			//Configura paginas filhas recursivo
			_navBarConfig(userId, navFilha, pFilha, navBarItens);
		}			
	}
	
	private NavBarItem navBarItemConfig(Serializable userId, Pagina pag, List<NavBarItem> navBarItens) {
		NavBarItem navBarItem = new NavBarItemImpl(pag.getId(), pag.getNome(), 
				pag.getURL(), pag.getIcone(), null, 
				pag.getOrdem());
		if(pag.isLinkBarra()) {
			navBarItens.add(navBarItem);
		}
		return navBarItem;
	}
}