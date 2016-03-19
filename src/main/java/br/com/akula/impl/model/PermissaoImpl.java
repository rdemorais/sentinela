package br.com.akula.impl.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.akula.api.model.EscopoPermissao;
import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.Permissao;

@Entity(name="Permissao")
@Table(name="tb_permissao_seg")
public class PermissaoImpl implements Permissao{
	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_permissao_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_permissao")
	private Long id;
	
	@Column(name="ds_nome", unique=true)
	private String nome;
	
	@Column(name="ds_nome_amigavel")
	private String nomeAmigavel;
	
	@Column(name="ds_descricao")
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_escopo")
	private EscopoPermissao escopo;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=PaginaImpl.class, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "co_pagina", referencedColumnName = "co_pagina")
	private Pagina pagina;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeAmigavel() {
		return nomeAmigavel;
	}
	public void setNomeAmigavel(String nomeAmigavel) {
		this.nomeAmigavel = nomeAmigavel;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public EscopoPermissao getEscopo() {
		return escopo;
	}
	public void setEscopo(EscopoPermissao escopo) {
		this.escopo = escopo;
	}
	public Pagina getPagina() {
		return pagina;
	}
	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}
	@Override
	public String toString() {
		return "PermissaoImpl [id=" + id + ", nome=" + nome + ", escopo="
				+ escopo + "]";
	}
}