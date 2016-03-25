package br.com.akula.impl.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import br.com.akula.api.ca.NavBarItem;
import br.com.akula.api.ca.UserDetails;
import br.com.akula.api.dao.SentinelaDao;
import br.com.akula.api.model.EscopoPermissao;
import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.PermissaoGrupoUsuario;
import br.com.akula.api.model.Usuario;
import br.com.akula.api.model.UsuarioGrupo;
import br.com.akula.api.service.SentinelaService;
import br.com.akula.impl.model.AbstractEntityPermissao;
import br.com.akula.impl.model.GrupoImpl;
import br.com.akula.impl.model.PermissaoGrupoUsuarioImpl;
import br.com.akula.impl.model.PermissaoImpl;
import br.com.akula.impl.model.UsuarioGrupoImpl;
import br.com.akula.impl.model.UsuarioImpl;

public class SentinelaServiceImpl implements SentinelaService {

	@Autowired
	private SentinelaDao sentinelaDao;
	
	private final PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Override
	@Transactional
	public void createUsuarioAutoRegistro(String login, String senha, String grupo, String pagina) throws RuntimeException {
		Usuario user = new UsuarioImpl();
		
		Grupo g = sentinelaDao.findGrupo(grupo);
		Pagina p = sentinelaDao.findPagina(pagina);
		
		user.setAtivo(Boolean.TRUE);
		user.setAutoRegistro(Boolean.TRUE);
		user.setLogin(login);
		user.setSenhaAlterada(Boolean.FALSE);
		user.setSenha(encoder.encode(senha));
		user.setPaginaPadrao(p);
		
		sentinelaDao.create(user);
		
		UsuarioGrupo userGrupo = new UsuarioGrupoImpl();
		userGrupo.setUsuario(user);
		userGrupo.setGrupo(g);
		
		sentinelaDao.create(userGrupo);
	}
	
	@Override
	@Transactional
	public <T> T findEntity(Class<T> c, Serializable pk) throws RuntimeException {
		return sentinelaDao.find(c, pk);
	}
	
	@Override
	@Transactional
	public void createGrupo(String nome, String identificadorUnico, Boolean administracao) throws RuntimeException {
		Grupo g = new GrupoImpl();
		
		g.setNome(identificadorUnico);
		g.setIdentificadorUnico(identificadorUnico);
		g.setAdministracao(administracao);
		
		sentinelaDao.merge(g);
	}
	
	@Override
	@Transactional
	public void updateGrupo(Grupo g) throws RuntimeException {
		sentinelaDao.merge(g);
	}
	
	@Override
	@Transactional
	public void deleteGrupo(Grupo g) throws RuntimeException {
		sentinelaDao.delete(g);
	}
	
	@Override
	public Grupo findGrupo(String identificadorUnico) throws RuntimeException {
		return sentinelaDao.findGrupo(identificadorUnico);
	}
	
	@Override
	@Transactional
	public void createPermissao(String perm, EscopoPermissao escopo) throws RuntimeException {
		Permissao p = new PermissaoImpl();
		p.setNome(perm);
		p.setEscopo(escopo);
		
		sentinelaDao.merge(p);
	}
	
	@Override
	public boolean checkPerm(Object obj, String perm, Object usuGrupo) throws RuntimeException {
		try {
			Usuario usuario = null;
			Grupo grupo = null;
			Permissao p = sentinelaDao.findPermissao(perm);
			
			if(usuGrupo instanceof UsuarioImpl) {
				usuario = (Usuario) usuGrupo;
			}else if(usuGrupo instanceof GrupoImpl) {
				grupo = (Grupo) usuGrupo;
			}
			
			Class<?> classPerm = discoveryClassPermName(obj);
			Object entityPerm = null;
			
			if(usuario != null) {
				
			}else if(grupo != null) {
				entityPerm = sentinelaDao.findEntityPermissao(classPerm.getSimpleName(), grupo, p);
				
			}
			
			return entityPerm == null ? false : true;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	@Transactional
	public void addPermissao(String perm, Object usuGrupo) throws RuntimeException {
		Usuario usuario = null;
		Grupo grupo = null;
		
		if(usuGrupo instanceof UsuarioImpl) {
			usuario = (Usuario) usuGrupo;
		}else if(usuGrupo instanceof GrupoImpl) {
			grupo = (Grupo) usuGrupo;
		}
		
		PermissaoGrupoUsuario permGU = new PermissaoGrupoUsuarioImpl();
		
		permGU.setGrupo(grupo);
		permGU.setUsuario(usuario);
		permGU.setPermissao(sentinelaDao.findPermissao(perm));
		
		sentinelaDao.merge(permGU);
	}

	@Override
	@Transactional
	public void addPermissao(String perm, Object obj, Grupo grupo, Usuario usuario) throws RuntimeException {
		try {
			//TODO checar se obj class extens AbstractEntityPermissao
			Permissao p = sentinelaDao.findPermissao(perm);
			String simpleName = obj.getClass().getSimpleName();
			
			String setUsuarioMethodName = "setUsuario";
			String setGrupoMethodName = "setGrupo";
			String setPermissaoMethodName = "setPermissao";
			String setModuloPMethodName = "set"+simpleName;
		
		
			Class<?> moduloPermissaoClazz = discoveryClassPermName(obj);
			Object moduloPermissaoInst = moduloPermissaoClazz.newInstance();
			
			Field fieldsEntityPerm[] = AbstractEntityPermissao.class.getDeclaredFields();
			
			Field fieldsClazz[] = moduloPermissaoClazz.getDeclaredFields();

			Field fields[] = new Field[fieldsEntityPerm.length + fieldsClazz.length];
			
			System.arraycopy(fieldsEntityPerm, 0, fields, 0, fieldsEntityPerm.length);
			
			System.arraycopy(fieldsClazz, 0, fields, fieldsEntityPerm.length, fieldsClazz.length);
			
			for (Field field : fields) {
				if(field.getName().equals("usuario")) {
					moduloPermissaoClazz.getMethod(setUsuarioMethodName, field.getType()).invoke(moduloPermissaoInst, usuario);
				}else if(field.getName().equals("grupo")) {
					moduloPermissaoClazz.getMethod(setGrupoMethodName, field.getType()).invoke(moduloPermissaoInst, grupo);
				}else if(field.getName().equals("permissao")) {
					moduloPermissaoClazz.getMethod(setPermissaoMethodName, field.getType()).invoke(moduloPermissaoInst, p);
				}else if(field.getName().equalsIgnoreCase(simpleName)) {
					moduloPermissaoClazz.getMethod(setModuloPMethodName, field.getType()).invoke(moduloPermissaoInst, obj);
				}
			}
			
			sentinelaDao.merge(moduloPermissaoInst);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (SecurityException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void addPermissao(String perm, Object obj, Grupo grupo) throws RuntimeException {
		addPermissao(perm, obj, grupo, null);
	}

	@Override
	@Transactional
	public void addPermissao(String perm, Object obj, Usuario usuario) throws RuntimeException {
		addPermissao(perm, obj, null, usuario);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String usuarioPaginaPadrao() throws RuntimeException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<UserDetails, Object> userDet = (Map<UserDetails, Object>) auth.getDetails();
		String paginaPadrao = (String)userDet.get(UserDetails.PAGINA_PADRAO);
		return paginaPadrao;
	}
	
	@Override
	public boolean necessitaAlterarSenha() throws RuntimeException {
		Usuario user = sentinelaDao.find(UsuarioImpl.class, usuarioLogadoId());
		return user.getSenhaAlterada();
	}
	
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
	
	private Class<?> discoveryClassPermName(Object obj) throws ClassNotFoundException {
		String simpleName = obj.getClass().getSimpleName();
		String pack = obj.getClass().getPackage().getName();
		String moduloPermissaoClazzName = pack + "." + simpleName+"Permissao";
		
		return Class.forName(moduloPermissaoClazzName);
	}
}