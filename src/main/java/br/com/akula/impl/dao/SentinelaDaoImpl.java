package br.com.akula.impl.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.com.akula.api.dao.SentinelaDao;
import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;

public class SentinelaDaoImpl implements SentinelaDao{

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void create(Object o) throws RuntimeException {
		try {
			em.persist(o);
			em.flush();
		} catch (PersistenceException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	public Object merge(Object o) throws RuntimeException {
		Object merged = em.merge(o);
		em.flush();
		return merged;
	}
	
	@Override
	public void delete(Object o) throws RuntimeException {
		em.remove(o);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> c, Serializable pk) throws RuntimeException {
		try {
			String nomeClass = c.getCanonicalName();
			//nomeClass += "Impl";
			return (T) em.find(Class.forName(nomeClass), pk);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public Permissao findPermissao(String perm) throws RuntimeException {
		try {
			Query q = em.createQuery("FROM Permissao p WHERE p.nome = :nome");
			q.setParameter("nome", perm);
			return (Permissao) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (QueryTimeoutException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	public Grupo findGrupo(String identificadorUnico) throws RuntimeException {
		try {
			Query q = em.createQuery("FROM Grupo g WHERE g.identificadorUnico = :idU");
			q.setParameter("idU", identificadorUnico);
			return (Grupo) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (QueryTimeoutException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	public Pagina findPagina(String identificadorUnico) throws RuntimeException {
		try {
			Query q = em.createQuery("FROM Pagina p WHERE p.identificadorUnico = :idU");
			q.setParameter("idU", identificadorUnico);
			return (Pagina) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (QueryTimeoutException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T findEntityPermissao(String entityName, Grupo g, Permissao perm) throws RuntimeException {
		try {
			Query q = em.createQuery("FROM "+entityName+" ent WHERE ent.grupo = :gru AND ent.permissao = :perm");
			q.setParameter("gru", g);
			q.setParameter("perm", perm);
			return (T) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (QueryTimeoutException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T findEntityPermissao(String entityName, Usuario u, Permissao perm) throws RuntimeException {
		try {
			Query q = em.createQuery("FROM "+entityName+" ent WHERE ent.usuario = :usu AND ent.permissao = :perm");
			q.setParameter("usu", u);
			q.setParameter("perm", perm);
			return (T) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (QueryTimeoutException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	public Usuario findUsuarioByIDFacebook(Long idFacebook) throws RuntimeException {
		try {
			Query q = em.createQuery("FROM Usuario u WHERE u.idFacebook = :idFB");
			q.setParameter("idFB", idFacebook);
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (QueryTimeoutException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	public Usuario findUsuario(String login) throws RuntimeException {
		try {
			Query q = em.createQuery("FROM Usuario u WHERE u.login = :l");
			q.setParameter("l", login);
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (QueryTimeoutException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Permissao> listPermissaoUsuario(Serializable id) throws RuntimeException {
		try{
			Query q = em.createQuery("SELECT perm.permissao FROM PermissaoGrupoUsuario perm WHERE "
					+ "perm.usuario.id = :idUser "
					+ "OR perm.grupo.id IN (SELECT ug.grupo.id FROM UsuarioGrupo ug WHERE ug.usuario.id = :idUser)");
			q.setParameter("idUser", id);
			return q.getResultList();
		}catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pagina> listPaginas(Serializable idUser, Pagina paginaPai, boolean paginaPadrao)
			throws RuntimeException {
		try {
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("SELECT distinct pag FROM Pagina pag ");
			if(idUser != null) {
				queryStr.append("INNER JOIN pag.permissoes perm ");
			}
			queryStr.append("WHERE ");
			if(idUser != null) {
				queryStr.append("perm.id IN (SELECT per.permissao.id FROM PermissaoGrupoUsuario per  ");
				queryStr.append("WHERE per.usuario.id = :idUser ");
				queryStr.append("OR per.grupo.id IN (SELECT ug.grupo.id FROM UsuarioGrupo ug WHERE ug.usuario.id = :idUser)");
				queryStr.append(")");
				queryStr.append(" AND ");
			}
			
			if(paginaPai != null) {
				queryStr.append("pag.paginaPai = :pai ");
			}else {
				if(paginaPadrao) {
					queryStr.append("pag.podeSerPaginaPadrao = TRUE ");
				}else {
					queryStr.append("pag.linkBarra = TRUE ");	
				}
			}
			
			queryStr.append("ORDER BY pag.ordem");
			Query q = em.createQuery(queryStr.toString());
			if(idUser != null) {
				q.setParameter("idUser", idUser);
			}
			if(paginaPai != null) {
				q.setParameter("pai", paginaPai);
			}
			return q.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}