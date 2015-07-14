package br.edu.ifpb.pweb.tsi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;
import br.edu.ifpb.pweb.tsi.model.TipoDefesa;

@ManagedBean(name = "alunoBean")
@RequestScoped
public class AlunoBean {

	private String matricula;	
	private String nomeAluno;
	private String tituloProjeto;
	private Double notaAluno;
	private Date dataApresentacao;
//	private Professor orientador;
//	private List<Professor> banca;
//	private List<Evento> eventos;
	private TipoDefesa tipo;
	private List<Aluno> alunos;
	
	
	public void MostrarTodos(){
		alunos = new ArrayList<Aluno>();
		AlunoDAO dao = new AlunoDAO();
		alunos = dao.readAll();
		System.out.println(alunos.toString());
		if(alunos.isEmpty()){
			System.out.println("vazio");
		}else{
			System.out.println(alunos.get(0).getDataApresentacao());
		}
		
	}
	
	public void CadastrarNoBanco(Aluno alu){
		AlunoDAO dao = new AlunoDAO();
		dao.begin();
		dao.create(alu);
		dao.commit();
	}
	public String SalvarAluno(){
		Aluno alu = new Aluno();
		alu.setDataApresentacao(this.dataApresentacao);
		alu.setMatricula(this.matricula);
		alu.setNotaAluno(this.notaAluno);
		alu.setNomeAluno(this.nomeAluno);
		alu.setTituloProjeto(this.tituloProjeto);
		System.out.println(alu.getDataApresentacao());
		CadastrarNoBanco(alu);
		
//		Aluno a = dao.read(matricula);
//
//		
//		
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, a.getNomeAluno(), "FUNFOU!");
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//		
		return "dashboard?faces-redirect=true";
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
	public TipoDefesa[] getTipo() {
		return tipo.values();
	}
	public void setTipo(TipoDefesa tipo) {
		this.tipo = tipo;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Date getDataApresentacao() {
		return dataApresentacao;
	}

	public void setDataApresentacao(Date dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}
	
}
