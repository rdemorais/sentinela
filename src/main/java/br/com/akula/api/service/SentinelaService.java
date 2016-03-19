package br.com.akula.api.service;

import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Usuario;

/**
 * Este servi&ccedil;o &eacute; representa a porta de entrada para as funcionalidades do Componente Sentinela.
 * <p>
 * Atrav&eacute;s dele &eacute; poss&iacute;vel manter permiss&otilde;es e eventos de auditoria.
 * 
 * @author Rafael de Morais - 19.03.2016
 *
 */
public interface SentinelaService {
	
	/**
	 * Vincula uma permiss&atilde;o j&aacute; cadastrada a um {@link Usuario} ou {@link Grupo}
	 * 
	 * @param perm a permiss&atilde;o a ser associada no formato ROLE_[PERM]
	 * @param usuGrupo objeto que representa o {@link Usuario} ou o {@link Grupo}
	 * @throws RuntimeException
	 */
	public void addPermissao(String perm, Object usuGrupo) throws RuntimeException;
	
	/**
	 * Vincula uma permiss&atilde;o existente a um objeto do tipo [MODULO]Permissao.
	 * <p>
	 * 
	 * Ex.:<p>
	 * <p>
	 * Considerando que o sistema cliente possua uma classe chamada Encaminhamento. &Eacutel poss&eacute;vel 
	 * criar uma nova classe do tipo EncaminhamentoPermissao e vincular a ela uma nova permiss&atilde;o, no n&iacute;vel do registro,
	 * a um {@link Usuario} ou {@link Grupo}.
	 * 
	 * @param perm a permiss&atilde;o a ser associada no formato ROLE_[PERM]
	 * @param obj o objeto que recebe a permiss&atilde;o
	 * @param grupo
	 * @param usuario
	 * @throws RuntimeException
	 */
	public void addPermissao(String perm, Object obj, Grupo grupo, Usuario usuario) throws RuntimeException;
	
	/**
	 * M&eacute;todo auxiliar que facilita o uso do addPermissao(String perm, Object obj, Grupo grupo, Usuario usuario) informando apenas o grupo.
	 * 
	 * @param perm 
	 * @param obj
	 * @param grupo
	 * @throws RuntimeException
	 */
	public void addPermissao(String perm, Object obj, Grupo grupo) throws RuntimeException;
	
	/**
	 * M&eacute;todo auxiliar que facilita o uso do addPermissao(String perm, Object obj, Grupo grupo, Usuario usuario) informando apenas o usuario.
	 * 
	 * @param perm 
	 * @param obj
	 * @param grupo
	 * @throws RuntimeException
	 */
	public void addPermissao(String perm, Object obj, Usuario usuario) throws RuntimeException;
}