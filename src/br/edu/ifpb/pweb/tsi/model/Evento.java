package br.edu.ifpb.pweb.tsi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Aluno codigoAluno;
	private Date dataEvento;
	@OneToMany(cascade= CascadeType.ALL)
	private List<Anotacoes> comentarios;
	
	public void setComentarios(List<Anotacoes> comentarios) {
		this.comentarios = comentarios;
	}
	@Enumerated(EnumType.STRING)
	private TipoEvento tipo;
	
	public TipoEvento getTipo() {
		return tipo;
	}
	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setComentarios(ArrayList<Anotacoes> comentarios) {
		this.comentarios = comentarios;
	}
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	public Aluno getCodigoAluno() {
		return codigoAluno;
	}
	public void setCodigoAluno(Aluno codigoAluno) {
		this.codigoAluno = codigoAluno;
	}
	public List<Anotacoes> getComentarios() {
		return comentarios;
	}
	
	
}
