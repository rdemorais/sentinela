package br.com.akula.impl.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.Permissao;

@Entity(name="Pagina")
@Table(name="tb_pagina_seg")
public class PaginaImpl implements Pagina {

	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_pagina_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_pagina")
	private Long id;
	
	@Column(name="ds_nome")
	private String nome;
	
	@Column(name="ds_identificador_unico")
	private String identificadorUnico;
	
	@Column(name="ds_url")
	private String url;
	
	@Column(name="ds_descricao")
	private String descricao;
	
	@Column(name="ds_icone")
	private String icone;
	
	/**
	 * Define a ordem de apresentação da página
	 */
	private Integer ordem;
	
	@Column(name="link_barra")
	private Boolean linkBarra = false;
	
	@Column(name="ind_ativo")
	private Boolean ativo;
	
	@Column(name="ind_pode_ser_pagina_padrao")
	private Boolean podeSerPaginaPadrao = false;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=PaginaImpl.class)
	@JoinColumn(name = "co_pagina_pai", referencedColumnName = "co_pagina", updatable = true, insertable = false)
	private Pagina paginaPai;
	
	@OneToMany(targetEntity=PermissaoImpl.class, cascade=CascadeType.MERGE, fetch=FetchType.LAZY, mappedBy="pagina")
	private Collection<Permissao> permissoes;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdentificadorUnico() {
		return identificadorUnico;
	}
	public void setIdentificadorUnico(String identificadorUnico) {
		this.identificadorUnico = identificadorUnico;
	}
	public String getURL() {
		return url;
	}
	public void setURL(String url) {
		this.url = url;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Pagina getPaginaPai() {
		return paginaPai;
	}
	public void setPaginaPai(Pagina pai) {
		this.paginaPai = pai;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean isLinkBarra() {
		return this.linkBarra;
	}
	public void setLinkBarra(Boolean linkBarra) {
		this.linkBarra = linkBarra;
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Collection<Permissao> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(Collection<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	public void setPodeSerPaginaPadrao(Boolean podeSerPaginaPadrao) {
		this.podeSerPaginaPadrao = podeSerPaginaPadrao;
	}
	public Boolean getPodeSerPaginaPadrao() {
		return podeSerPaginaPadrao;
	}
	@Override
	public String toString() {
		return "PaginaImpl [id=" + id + ", nome=" + nome + ", url=" + url
				+ ", descricao=" + descricao + ", icone=" + icone + ", ordem="
				+ ordem + ", linkBarra=" + linkBarra + ", ativo=" + ativo + "]";
	}
}