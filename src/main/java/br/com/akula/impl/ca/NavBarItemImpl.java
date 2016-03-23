package br.com.akula.impl.ca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.akula.api.ca.NavBarItem;

public class NavBarItemImpl implements NavBarItem, Serializable{

	private static final long serialVersionUID = -4540950653472220964L;

	private Long id;
	private String nomeURL;
	private String url;
	private String icone;
	private Boolean active = false;
	private Integer ordem;
	
	private List<NavBarItem> itens = new ArrayList<NavBarItem>();
	
	public NavBarItemImpl(Long id, String nomeURL, String url, String icone,
			Boolean active, Integer ordem) {
		this.id = id;
		this.nomeURL = nomeURL;
		this.url = url;
		this.icone = icone;
		this.active = active;
		this.ordem = ordem;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeURL() {
		return nomeURL;
	}
	public void setNomeURL(String nomeURL) {
		this.nomeURL = nomeURL;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	
	public void addItem(NavBarItem item) {
		this.itens.add(item);
	}
	
	public List<NavBarItem> getItens() {
		return itens;
	}

	public void setItens(List<NavBarItem> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "NavBarItemImpl [id=" + id + ", nomeURL=" + nomeURL + ", url="
				+ url + ", icone=" + icone + ", active=" + active + ", ordem="
				+ ordem + "]";
	}
}