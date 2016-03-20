package br.com.akula.impl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.akula.api.model.Grupo;

@Entity(name="Grupo")
@Table(name="tb_grupo_seg")
public class GrupoImpl implements Grupo{
	
	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_grupo_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_grupo")
	private Long id;
	
	@Column(name="ds_nome")
	private String nome;
	
	@Column(name="ds_identificador_unico")
	private String identificadorUnico;
	
	@Column(name="ind_administracao")
	private Boolean administracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public String getIdentificadorUnico() {
		return identificadorUnico;
	}

	public void setIdentificadorUnico(String identificadorUnico) {
		this.identificadorUnico = identificadorUnico;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAdministracao() {
		return administracao;
	}

	public void setAdministracao(Boolean administracao) {
		this.administracao = administracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identificadorUnico == null) ? 0 : identificadorUnico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoImpl other = (GrupoImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificadorUnico == null) {
			if (other.identificadorUnico != null)
				return false;
		} else if (!identificadorUnico.equals(other.identificadorUnico))
			return false;
		return true;
	}
}