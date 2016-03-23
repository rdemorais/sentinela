package br.com.akula.api.ca;

import java.util.List;

public interface NavBarItem {
	public Long getId();
	public void setId(Long id);
	public String getNomeURL();
	public void setNomeURL(String nomeUrl);
	public String getUrl();
	public void setUrl(String url);
	public String getIcone();
	public void setIcone(String icone);
	public Boolean getActive();
	public void setActive(Boolean active);
	public Integer getOrdem();
	public void setOrdem(Integer ordem);
	public void addItem(NavBarItem item);
	public List<NavBarItem> getItens();
	public void setItens(List<NavBarItem> itens);
}