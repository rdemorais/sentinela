package br.com.akula.api.model;

import java.util.Date;

public interface Auditavel {
	public void setUsuarioInsert(Usuario u);
	public Usuario getUsuarioInsert();
	public void setDataHoraInsert(Date dtHrInsert);
	public Date getDataHoraInsert();
	public void setUsuarioUpdate(Usuario u);
	public Usuario getUsuarioUpdate();
	public void setDataHoraUpdate(Date dtHrUpdate);
	public Date getDataHoraUpdate();
	public void setUsuarioDelete(Usuario u);
	public Usuario getUsuarioDelete();
	public void setDataHoraDelete(Date dtHrDelete);
	public Date getDataHoraDelete();
}