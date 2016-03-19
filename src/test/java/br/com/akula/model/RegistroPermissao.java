package br.com.akula.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.akula.impl.model.AbstractEntityPermissao;

@Entity(name="RegistroPermissao")
@Table(name="tb_registro_permissao_test")
public class RegistroPermissao extends AbstractEntityPermissao {
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Registro.class, cascade=CascadeType.REFRESH, optional=true)
	@JoinColumn(name = "co_registro", referencedColumnName = "co_registro")
	private Registro registro;

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
}