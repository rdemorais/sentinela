package br.com.akula.impl.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.akula.api.model.Auditavel;
import br.com.akula.api.model.Usuario;

@MappedSuperclass
public abstract class AbstractAuditavel implements Auditavel{
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=UsuarioImpl.class, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "co_usuario_insert", referencedColumnName = "co_usuario")
	private Usuario usuarioInsert;
	
	@Column(name="dt_insert")
	private Date dthrUsuarioInsert;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=UsuarioImpl.class, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "co_usuario_update", referencedColumnName = "co_usuario")
	private Usuario usuarioUpdate;
	
	@Column(name="dt_update")
	private Date dthrUsuarioUpdate;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=UsuarioImpl.class, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "co_usuario_delete", referencedColumnName = "co_usuario")
	private Usuario usuarioDelete;
	
	@Column(name="dt_delete")
	private Date dthrUsuarioDelete;
	
	@Override
	public void setUsuarioInsert(Usuario u) {
		this.usuarioInsert = u;
	}

	@Override
	public Usuario getUsuarioInsert() {
		return this.usuarioInsert;
	}

	@Override
	public void setDataHoraInsert(Date dtHrInsert) {
		this.dthrUsuarioInsert = dtHrInsert;
	}

	@Override
	public Date getDataHoraInsert() {
		return this.dthrUsuarioInsert;
	}

	@Override
	public void setUsuarioUpdate(Usuario u) {
		this.usuarioUpdate = u;
	}

	@Override
	public Usuario getUsuarioUpdate() {
		return this.usuarioUpdate;
	}

	@Override
	public void setDataHoraUpdate(Date dtHrUpdate) {
		this.dthrUsuarioUpdate = dtHrUpdate;
	}

	@Override
	public Date getDataHoraUpdate() {
		return this.dthrUsuarioUpdate;
	}

	@Override
	public Usuario getUsuarioDelete() {
		return this.usuarioDelete;
	}

	@Override
	public void setDataHoraDelete(Date dtHrDelete) {
		this.dthrUsuarioDelete = dtHrDelete;
	}

	@Override
	public Date getDataHoraDelete() {
		return this.dthrUsuarioDelete;
	}

	@Override
	public void setUsuarioDelete(Usuario u) {
		this.usuarioDelete = u;
	}
}