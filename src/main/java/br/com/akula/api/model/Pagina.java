package br.com.akula.api.model;

import java.util.Collection;

public interface Pagina {
	public Long getId();
	public void setId(Long id);
	public String getNome();
	public void setNome(String nome);
	public String getIdentificadorUnico();
	public void setIdentificadorUnico(String identificadorUnico);
	public String getURL();
	public void setURL(String url);
	public String getIcone();
	public void setIcone(String icone);
	public String getDescricao();
	public void setDescricao(String descricao);
	/**
	 * Informa se o link deve ser apresentado na barra de navegação
	 * REGRA: A página não pode ser filha de outra, portanto PaginaPai = null
	 * 
	 */
	public Boolean isLinkBarra();
	public void setLinkBarra(Boolean linkBarra);
	public Integer getOrdem();
	public void setOrdem(Integer ordem);
	public Pagina getPaginaPai();
	public void setPaginaPai(Pagina pai);
	public Boolean getAtivo();
	public void setAtivo(Boolean ativo);
	public Collection<Permissao> getPermissoes();
	public void setPermissoes(Collection<Permissao> permissoes);
	public void setPodeSerPaginaPadrao(Boolean podeSerPaginaPadrao);
	public Boolean getPodeSerPaginaPadrao();
}