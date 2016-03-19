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
	
}