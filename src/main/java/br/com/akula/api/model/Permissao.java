package br.com.akula.api.model;
public interface Permissao {
	public Long getId();
	public void setId(Long id);
	public String getNome();
	public void setNome(String nome);
	public String getNomeAmigavel();
	public void setNomeAmigavel(String nomeAmigavel);
	public String getDescricao();
	public void setDescricao(String descricao);
	public EscopoPermissao getEscopo();
	public void setEscopo(EscopoPermissao escopo);
	public Pagina getPagina();
	public void setPagina(Pagina pagina);
}