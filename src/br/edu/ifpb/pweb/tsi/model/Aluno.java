package br.edu.ifpb.pweb.tsi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Aluno {
	@Id
	@Column(length = 11)
	private String matricula;
	
	@Column(length = 80)
	private String nomeAluno;
	
	@Column(length = 100)
	private String tituloProjeto;
	
	@OneToOne
	private Professor orientador;
	private Double notaAluno;
	
	@Temporal(TemporalType.DATE)
	private Date dataApresentacao;
	
	@OneToMany
	@JoinTable(name="bancaProfessor", joinColumns={@JoinColumn(name="idAluno", referencedColumnName="matricula")}, inverseJoinColumns={@JoinColumn(name="idProfessor", referencedColumnName="matricula")})
	private List<Professor> banca;
	
	@OneToMany
	@JoinTable(name="eventosAluno", joinColumns={@JoinColumn(name="idAluno", referencedColumnName="matricula")}, inverseJoinColumns={@JoinColumn(name="idEvento", referencedColumnName="id")})
	private List<Evento> eventos;
	
	@Enumerated(EnumType.STRING)
	private TipoDefesa tipo;
	
	public Aluno(String matricula, String nomeAluno) {
		super();
		this.matricula = matricula;
		this.nomeAluno = nomeAluno;
	}
	public Aluno() {
		super();
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getTituloProjeto() {
		return tituloProjeto;
	}
	public void setTituloProjeto(String tituloProjeto) {
		this.tituloProjeto = tituloProjeto;
	}
	public Professor getOrientador() {
		return orientador;
	}
	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}
	public Double getNotaAluno() {
		return notaAluno;
	}
	public void setNotaAluno(Double notaAluno) {
		this.notaAluno = notaAluno;
	}
	public Date getDataApresentacao() {
		return dataApresentacao;
	}
	public void setDataApresentacao(Date dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}
	public List<Professor> getBanca() {
		return banca;
	}
	public void setBanca(List<Professor> banca) {
		this.banca = banca;
	}
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	public TipoDefesa getTipo() {
		return tipo;
	}
	public void setTipo(TipoDefesa tipo) {
		this.tipo = tipo;
	}
	public void addEvento(Evento e){
		this.eventos.add(e);
	}
	
	
}
