package br.com.akula.impl.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.akula.api.model.Pagina;
import br.com.akula.api.model.PermissaoGrupoUsuario;
import br.com.akula.api.model.Usuario;

@Entity(name="Usuario")
@Table(name="tb_usuario_seg")
public class UsuarioImpl implements Usuario {

	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_usuario_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_usuario")
	private Long id;
	
	@Column(name="ds_login", unique=true, nullable=false)
	private String login;
	
	@Column(name="ds_senha")
	private String senha;
	
	@Column(name="ind_ativo")
	private Boolean ativo;
	
	@Column(name="ind_auto_registro")
	private Boolean autoRegistro;
	
	@Column(name="ind_senha_alterada")
	private Boolean senhaAlterada;
	
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="usuario", targetEntity=PermissaoGrupoUsuarioImpl.class)
	private Collection<PermissaoGrupoUsuario> permissoesUsuario;
	
	@OneToOne(targetEntity=PaginaImpl.class, cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="co_pagina")
	private Pagina paginaPadrao;
	
	@Column(name="ds_tenant_id")
	private String tenantId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setAutoRegistro(Boolean autoRegistro) {
		this.autoRegistro = autoRegistro;
	}
	
	public Boolean getAutoRegistro() {
		return autoRegistro;
	}
	
	public Boolean getSenhaAlterada() {
		return senhaAlterada;
	}

	public void setSenhaAlterada(Boolean senhaAlterada) {
		this.senhaAlterada = senhaAlterada;
	}

	public Collection<PermissaoGrupoUsuario> getPermissoesUsuario() {
		return permissoesUsuario;
	}

	public void setPermissoesUsuario(
			Collection<PermissaoGrupoUsuario> permissoesUsuario) {
		this.permissoesUsuario = permissoesUsuario;
	}

	public Pagina getPaginaPadrao() {
		return paginaPadrao;
	}

	public void setPaginaPadrao(Pagina paginaPadrao) {
		this.paginaPadrao = paginaPadrao;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}