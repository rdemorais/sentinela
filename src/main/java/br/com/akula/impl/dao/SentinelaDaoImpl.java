package br.com.akula.impl.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.com.akula.api.dao.SentinelaDao;
import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;

public class SentinelaDaoImpl implements SentinelaDao{

	@PersistenceContext
	protected EntityManager em;
	
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
			nomeClass += "Impl";
			return (T) em.find(Class.forName(nomeClass), pk);
		} catch (ClassNotFoundException e) {
			//TODO Informar que o prefixo da classe concreta deve ser "Impl" e o pacote deve ser o mesmo que a interface
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
}