package br.com.akula.api.model;

public interface UsuarioGrupo {
	public Long getId();
	public void setId(Long id);
	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	public Grupo getGrupo();
	public void setGrupo(Grupo grupo);
}