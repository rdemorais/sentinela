package br.com.akula.api.dao;

import java.io.Serializable;

import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;

public interface SentinelaDao {
	public Object merge(Object o) throws RuntimeException;
	public void delete(Object o) throws RuntimeException;
	public <T> T find(Class<T> c, Serializable pk) throws RuntimeException;
	public Permissao findPermissao(String perm) throws RuntimeException;
	public Grupo findGrupo(String identificadorUnico) throws RuntimeException;
	public <T> T findEntityPermissao(String entityName, Grupo g, Permissao perm) throws RuntimeException;
	public <T> T findEntityPermissao(String entityName, Usuario u, Permissao perm) throws RuntimeException;
}