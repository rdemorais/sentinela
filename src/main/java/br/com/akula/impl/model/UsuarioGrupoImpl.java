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
import br.com.akula.api.model.Usuario;
import br.com.akula.api.model.UsuarioGrupo;

@Entity(name="UsuarioGrupo")
@Table(name="tb_usuario_grupo_seg")
public class UsuarioGrupoImpl implements UsuarioGrupo{
	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_usuario_grupo_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_usuario_grupo")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=UsuarioImpl.class, cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name = "co_usuario", referencedColumnName = "co_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=GrupoImpl.class, cascade=CascadeType.REFRESH, optional=false)
	@JoinColumn(name = "co_grupo", referencedColumnName = "co_grupo")
	private Grupo grupo;

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
		this.usuario = usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "UsuarioGrupoImpl [id=" + id + ", usuario=" + usuario
				+ ", grupo=" + grupo + "]";
	}
}