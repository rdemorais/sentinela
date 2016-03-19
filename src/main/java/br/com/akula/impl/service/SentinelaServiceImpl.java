package br.com.akula.impl.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.akula.api.dao.SentinelaDao;
import br.com.akula.api.model.EscopoPermissao;
import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.PermissaoGrupoUsuario;
import br.com.akula.api.model.Usuario;
import br.com.akula.api.service.SentinelaService;
import br.com.akula.impl.model.AbstractEntityPermissao;
import br.com.akula.impl.model.GrupoImpl;
import br.com.akula.impl.model.PermissaoGrupoUsuarioImpl;
import br.com.akula.impl.model.PermissaoImpl;
import br.com.akula.impl.model.UsuarioImpl;

public class SentinelaServiceImpl implements SentinelaService {

	@Autowired
	private SentinelaDao sentinelaDao;
	
	
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
			String pack = obj.getClass().getPackage().getName();
			String moduloPermissaoClazzName = pack + "." + simpleName+"Permissao";
			
			String setUsuarioMethodName = "setUsuario";
			String setGrupoMethodName = "setGrupo";
			String setPermissaoMethodName = "setPermissao";
			String setModuloPMethodName = "set"+simpleName;
		
		
			Class<?> moduloPermissaoClazz = Class.forName(moduloPermissaoClazzName);
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
}