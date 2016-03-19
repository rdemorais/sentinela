package br.com.akula.impl.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.PermissaoGrupoUsuario;
import br.com.akula.api.model.Usuario;

@Entity(name="PermissaoGrupoUsuario")
@Table(name="tb_permissao_grupo_usuario_seg")
public class PermissaoGrupoUsuarioImpl implements PermissaoGrupoUsuario {

	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_per_grupo_usuario_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_per_grupo_usuario")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.REFRESH, optional=true, targetEntity=UsuarioImpl.class)
	@JoinColumn(name="co_usuario")
	private Usuario usuario;
	
	@ManyToOne(cascade=CascadeType.REFRESH, optional=true, fetch=FetchType.LAZY, targetEntity=GrupoImpl.class)
	@JoinColumn(name="co_grupo")
	private Grupo grupo;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=PermissaoImpl.class, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "co_permissao", referencedColumnName = "co_permissao")
	private Permissao permissao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = (UsuarioImpl) usuario;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = (GrupoImpl) grupo;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}