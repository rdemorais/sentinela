package br.com.akula.api.dao;

import java.io.Serializable;
import java.util.List;

import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;

public interface SentinelaDao {
	public void create(Object o) throws RuntimeException;
	public Object merge(Object o) throws RuntimeException;
	public void delete(Object o) throws RuntimeException;
	public <T> T find(Class<T> c, Serializable pk) throws RuntimeException;
	public Permissao findPermissao(String perm) throws RuntimeException;
	public Grupo findGrupo(String identificadorUnico) throws RuntimeException;
	public Pagina findPagina(String identificadorUnico) throws RuntimeException;
	public <T> T findEntityPermissao(String entityName, Grupo g, Permissao perm) throws RuntimeException;
	public <T> T findEntityPermissao(String entityName, Usuario u, Permissao perm) throws RuntimeException;
	public Usuario findUsuarioByIDFacebook(Long idFacebook) throws RuntimeException;
	public Usuario findUsuario(String login) throws RuntimeException;
	public List<Permissao> listPermissaoUsuario(Serializable id) throws RuntimeException;
	public List<Pagina> listPaginas(Serializable idUser, Pagina paginaPai, boolean paginaPadrao) throws RuntimeException;
}