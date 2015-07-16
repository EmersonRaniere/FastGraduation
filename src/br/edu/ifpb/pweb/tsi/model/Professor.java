package br.edu.ifpb.pweb.tsi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Professor {
	@Id
	@Column(length = 11)
	private String matricula;
	
	@Column(length = 80)
	private String nome;

	public String getNome() {
		return nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
