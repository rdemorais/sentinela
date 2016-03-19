package br.com.akula.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Registro")
@Table(name="tb_registro_test")
public class Registro {
	
	@Id
	@SequenceGenerator(name="Sentinela_SeqGen", sequenceName="sentinela_registro_seq")
	@GeneratedValue(generator="Sentinela_SeqGen")
	@Column(name="co_registro")
	private Long id;
	
	private String nome;

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
}