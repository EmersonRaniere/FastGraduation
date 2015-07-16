package br.edu.ifpb.pweb.tsi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import sun.security.util.Length;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length=13)
	private String codigoAluno;
	
	private Date dataEvento;
	private boolean encerrado;
	
	@OneToMany
	private List<Anotacoes> comentarios;
	
	@Enumerated(EnumType.STRING)
	private TipoEvento tipo;
	
	public Evento(){};
	
	public Evento(String codigoAluno, Date dataEvento, boolean encerrado,
			TipoEvento tipo) {
		super();
		this.codigoAluno = codigoAluno;
		this.dataEvento = dataEvento;
		this.encerrado = encerrado;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoAluno() {
		return codigoAluno;
	}

	public void setCodigoAluno(String codigoAluno) {
		this.codigoAluno = codigoAluno;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public List<Anotacoes> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Anotacoes> comentarios) {
		this.comentarios = comentarios;
	}

	public TipoEvento getTipo() {
		return tipo;
	}
	
	public String getTipoEvento(){
		return tipo.getLabel();
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public boolean isencerrado() {
		return encerrado;
	}

	public void setencerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}
	
	
	
}
