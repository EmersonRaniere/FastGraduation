package br.edu.ifpb.pweb.tsi.bean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;

@ManagedBean(name = "alunoBean")
@SessionScoped
public class AlunoBean {

	private String matricula;	
	private String nomeAluno;
	private String tituloProjeto;
//	private Professor orientador;
	private Double notaAluno;
	private Date dataApresentacao;
//	private List<Professor> banca;
//	private List<Evento> eventos;
//	private TipoDefesa tipo;
	
	public String Salvar(){
		Aluno a1 = new Aluno();
		a1.setDataApresentacao(dataApresentacao);
		a1.setMatricula(matricula);
		a1.setNotaAluno(notaAluno);
		a1.setNomeAluno(nomeAluno);
		a1.setTituloProjeto(tituloProjeto);
		AlunoDAO dao = new AlunoDAO();
		dao.begin();
		dao.create(a1);
		dao.commit();
		
		Aluno a = dao.read(matricula);
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, a.getNomeAluno(), "FUNFOU!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return null;
	}
	
	public void Exibir(){
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.read(matricula);
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, a.getNomeAluno(), "FUNFOU!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
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
//	public TipoDefesa getTipo() {
//		return tipo;
//	}
//	public void setTipo(TipoDefesa tipo) {
//		this.tipo = tipo;
//	}
}
