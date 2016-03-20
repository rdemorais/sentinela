package br.com.akula.api.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Representa os usuários do sistema
 * 
 * @author Rafael de Morais
 *
 */
public interface Usuario extends Serializable{
	public Long getId();
	public void setId(Long id);
	public String getLogin();
	public void setLogin(String login);
	public String getSenha();
	public void setSenha(String senha);
	public Boolean isAtivo();
	public void setAtivo(Boolean ativo);
	public Boolean getAutoRegistro();
	public void setAutoRegistro(Boolean autoRegistro);
	public Boolean getSenhaAlterada();
	public void setSenhaAlterada(Boolean senhaAlterada);
	
	public Collection<PermissaoGrupoUsuario> getPermissoesUsuario();
	public void setPermissoesUsuario(Collection<PermissaoGrupoUsuario> permissoesUsuario);
	
	public Pagina getPaginaPadrao();
	public void setPaginaPadrao(Pagina paginaPadrao);
	
	public String getTenantId();
	public void setTenantId(String tenantId);
}