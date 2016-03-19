package br.com.akula.api.model;

public interface PermissaoGrupoUsuario {
	
	public Long getId();
	public void setId(Long id);
	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	public Permissao getPermissao();
	public void setPermissao(Permissao permissao);
	public void setGrupo(Grupo grupo);
	public Grupo getGrupo();
}