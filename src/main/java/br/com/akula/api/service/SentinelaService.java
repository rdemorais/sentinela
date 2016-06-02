package br.com.akula.api.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.akula.api.ca.NavBarItem;
import br.com.akula.api.model.EscopoPermissao;
import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;
import br.com.akula.impl.model.AbstractEntityPermissao;

/**
 * Este servi&ccedil;o &eacute; representa a porta de entrada para as funcionalidades do Componente Sentinela.
 * <br>
 * Atrav&eacute;s dele &eacute; poss&iacute;vel manter permiss&otilde;es e eventos de auditoria.
 * <br>
 * <strong>Uso do m&eacute;todo addPermissao()</strong>
 * <br>
 * Para alcan&ccedil;ar o potencial deste componente, o desenvolvedor deve considerar as seguintes premissas:
 * <br>
 * Assuma para este exemplo que a classe a qual se quer dar as pemiss&otilde;es chama-se <i>Registro</i>
 * <br>
 * <ul>
 * <li>Deve-se criar uma classe no mesmo pacote chamada RegistroPermissao</li>
 * <li>RegistroPermissao deve extender {@link AbstractEntityPermissao}</li>
 * <li>RegistroPermissao deve possuir um atributo chamado Registro</li>
 * </ul>
 * 
 * @author Rafael de Morais - 19.03.2016
 *
 */
public interface SentinelaService {
	
	
	/**
	 * Realiza o logout de um {@link Usuario}
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws RuntimeException ...
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws RuntimeException;
	
	/**
	 * Verifica se o {@link Usuario} existe a partir do login
	 * @param login o login que se deseja verificar
	 * @return true se o {@link Usuario} existir
	 * @throws RuntimeException ...
	 */
	public boolean usuarioExiste(String login) throws RuntimeException;
	
	/**
	 * Cria um novo {@link Usuario} 
	 * 
	 * @param login o login. Recomenda-se usar o email
	 * @param senha a senha
	 * @param grupo O {@link Grupo} de acesso. Identificador unico
	 * @param pagina A {@link Pagina}. Identificador unico
	 * @throws RuntimeException ...
	 */
	public Usuario createUsuarioAutoRegistro(String login, String senha, String grupo, String pagina) throws RuntimeException;
	
	/**
	 * Cria um novo grupo
	 * 
	 * @param nome nome do {@link Grupo}
	 * @param identificadorUnico nome &uacute;nico do {@link Grupo}
	 * @param administracao informa se este {@link Grupo} faz parte da administra&ccedil;&atilde;o do sistema
	 * @throws RuntimeException se algo der errado...
	 */
	public void createGrupo(String nome, String identificadorUnico, Boolean administracao) throws RuntimeException;
	
	/**
	 * Atualiza um grupo
	 * 
	 * @param g o {@link Grupo} a ser atualizado
	 * @throws RuntimeException se algo der errado...
	 */
	public void updateGrupo(Grupo g) throws RuntimeException;
	
	/**
	 * Apaga um grupo da base
	 * @param g o {@link Grupo} a ser exclu&iacute;do
	 * @throws RuntimeException se algo der errado...
	 */
	public void deleteGrupo(Grupo g) throws RuntimeException;
	
	/**
	 * Encontra um {@link Grupo} a partir do identificador
	 * 
	 * @param identificadorUnico localiza o {@link Grupo} por ser identificador
	 * @return retorna o {@link Grupo} encontrado ou null
	 * @throws RuntimeException se algo der errado...
	 */
	public Grupo findGrupo(String identificadorUnico) throws RuntimeException;
	
	/**
	 * Cria uma nova permiss&atilde;o
	 * 
	 * @param perm o nome da {@link Permissao} a ser criada
	 * @param escopo O {@link EscopoPermissao}
	 * @throws RuntimeException se algo der errado...
	 */
	public void createPermissao(String perm, EscopoPermissao escopo) throws RuntimeException;
	
	/**
	 * Verifica se um {@link Grupo} ou {@link Usuario} possui uma determinada {@link Permissao} no objeto
	 * 
	 * @param obj inst&acirc;ncia na qual se deseja verificar a {@link Permissao}
	 * @param perm a descri&ccedil;&atilde;o da {@link Permissao}
	 * @param usuGrupo um objeto que pode ser ou {@link Usuario} ou {@link Grupo}
	 * @return true houver uma {@link Permissao} relacionada
	 * @throws RuntimeException se algo der errado...
	 */
	public boolean checkPerm(Object obj, String perm, Object usuGrupo) throws RuntimeException;
	
	/**
	 * Vincula uma permiss&atilde;o j&aacute; cadastrada a um {@link Usuario} ou {@link Grupo}
	 * 
	 * @param perm a permiss&atilde;o a ser associada no formato ROLE_[PERM]
	 * @param usuGrupo objeto que representa o {@link Usuario} ou o {@link Grupo}
	 * @throws RuntimeException se algo der errado...
	 */
	public void addPermissao(String perm, Object usuGrupo) throws RuntimeException;
	
	/**
	 * Vincula uma permiss&atilde;o existente a um objeto do tipo [MODULO]Permissao.
	 * <br>
	 * 
	 * Ex.:<br>
	 * <br>
	 * Considerando que o sistema cliente possua uma classe chamada Encaminhamento. &Eacute; poss&eacute;vel 
	 * criar uma nova classe do tipo EncaminhamentoPermissao e vincular a ela uma nova permiss&atilde;o, no n&iacute;vel do registro,
	 * a um {@link Usuario} ou {@link Grupo}.
	 * 
	 * @param perm a permiss&atilde;o a ser associada no formato ROLE_[PERM]
	 * @param obj o objeto que recebe a permiss&atilde;o
	 * @param grupo o {@link Grupo}
	 * @param usuario o {@link Usuario}
	 * @throws RuntimeException se algo der errado...
	 */
	public void addPermissao(String perm, Object obj, Grupo grupo, Usuario usuario) throws RuntimeException;
	
	/**
	 * M&eacute;todo auxiliar que facilita o uso do addPermissao(String perm, Object obj, Grupo grupo, Usuario usuario) informando apenas o grupo.
	 * 
	 * @param perm a descri&ccedil;&atilde;o da {@link Permissao}
	 * @param obj o objeto cuja classe implementa {@link AbstractEntityPermissao}
	 * @param grupo o {@link Grupo}
	 * @throws RuntimeException se algo der errado...
	 */
	public void addPermissao(String perm, Object obj, Grupo grupo) throws RuntimeException;
	
	/**
	 * M&eacute;todo auxiliar que facilita o uso do addPermissao(String perm, Object obj, Grupo grupo, Usuario usuario) informando apenas o usuario.
	 * 
	 * @param perm a descri&ccedil;&atilde;o da {@link Permissao}
	 * @param obj o objeto cuja classe implementa {@link AbstractEntityPermissao}
	 * @param usuario o {@link Usuario}
	 * @throws RuntimeException se algo der errado...
	 */
	public void addPermissao(String perm, Object obj, Usuario usuario) throws RuntimeException;
	
	/**
	 * Localiza uma entidade no banco.
	 * 
	 * @param <T> tipo
	 * @param c a classe a cujo objeto ser&aacute; localizado
	 * @param pk a chave prim&aacute;ria
	 * @throws RuntimeException se algo der errado...
	 * @return retorna o objeto ou null
	 */
	public <T> T findEntity(Class<T> c, Serializable pk) throws RuntimeException;
	
	/**
	 * Retorna a p&aacute;gina padr&atilde;o configurada para o {@link Usuario}
	 * 
	 * @return a {@link Pagina} em string
	 * @throws RuntimeException se algo der errado...
	 */
	public String usuarioPaginaPadrao() throws RuntimeException;
	
	/**
	 * Atualiza a pagina padrao do {@link Usuario} logado no contexto
	 * @param novaPagina A identificacao unica da pagina
	 * @throws RuntimeException ...
	 */
	public void atualizaUsuarioPaginaPadrao(String novaPagina) throws RuntimeException;
	
	/**
	 * Verifica se o {@link Usuario} precisa alterar a senha. Normalmente acontece quando uma nova 
	 * senha &eacute; gerada pelo sistema
	 * 
	 * @return true se h&aacute; necessidade alterar a senha
	 * @throws RuntimeException se algo der errado...
	 */
	public boolean necessitaAlterarSenha() throws RuntimeException;
	
	/**
	 * Retorna o ID (chave) do {@link Usuario} logado
	 * 
	 * @return a chave primaria que identifica o {@link Usuario}
	 * @throws RuntimeException se algo der errado...
	 */
	public Serializable usuarioLogadoId() throws RuntimeException;
	
	/**
	 * Retorna o {@link Usuario} logado
	 * 
	 * @return o {@link Usuario}
	 * @throws RuntimeException se algo der errado...
	 */
	public Usuario usuarioLogado() throws RuntimeException;
	
	/**
	 * Retorna a lista de {@link NavBarItem} do {@link Usuario} logado
	 * @return a lista {@link NavBarItem}
	 * @throws RuntimeException ...
	 */
	public List<NavBarItem> usuarioNavBar() throws RuntimeException;
}