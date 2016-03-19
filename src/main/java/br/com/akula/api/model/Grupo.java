package br.com.akula.api.model;

public interface Grupo {
	public Long getId();
	public void setId(Long id);
	public String getNome();
	public void setNome(String nome);
	public String getIdentificadorUnico();
	public void setIdentificadorUnico(String identificadorUnico);
	public Boolean getAdministracao();
	public void setAdministracao(Boolean administracao);
}