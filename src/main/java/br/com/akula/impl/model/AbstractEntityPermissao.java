package br.com.akula.impl.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import br.com.akula.api.model.EntityPermissao;
import br.com.akula.api.model.Grupo;
import br.com.akula.api.model.Permissao;
import br.com.akula.api.model.Usuario;

@MappedSuperclass
public abstract class AbstractEntityPermissao implements EntityPermissao{

	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_entity_permissao_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_entity_permissao")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=UsuarioImpl.class, cascade=CascadeType.REFRESH, optional=true)
	@JoinColumn(name = "co_usuario", referencedColumnName = "co_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=GrupoImpl.class, cascade=CascadeType.REFRESH, optional=true)
	@JoinColumn(name = "co_grupo", referencedColumnName = "co_grupo")
	private Grupo grupo;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=PermissaoImpl.class, cascade=CascadeType.REFRESH, optional=false)
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
		this.usuario = usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public String toString() {
		return "EntityPermissaoImpl [id=" + id + ", usuario="
				+ usuario + ", grupo=" + grupo + ", permissao=" + permissao
				+ "]";
	}

}
