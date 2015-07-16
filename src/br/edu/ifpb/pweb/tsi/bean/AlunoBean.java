package br.edu.ifpb.pweb.tsi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;
import br.edu.ifpb.pweb.tsi.model.Evento;
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
//	@ManagedProperty(value="eventoBean")
//	private List<Evento> eventos;

	private TipoDefesa tipo;
	private List<Aluno> alunos;
	
	public Aluno alunoSelecionado;
	
	
	public void mostrarIndividual(){
		AlunoDAO dao = new AlunoDAO();
		alunoSelecionado = dao.read(this.matricula);
	}
	public void editarMostrarIndividual(){
		AlunoDAO dao = new AlunoDAO();
		alunoSelecionado = dao.read(this.matricula);
		this.nomeAluno = alunoSelecionado.getNomeAluno();
		this.tituloProjeto = alunoSelecionado.getTituloProjeto();
		this.notaAluno = alunoSelecionado.getNotaAluno();
		this.tipo = alunoSelecionado.getTipo();
		this.dataApresentacao = alunoSelecionado.getDataApresentacao();
//		this.orientador = alunoSelecionado.get();
//		this.nomeAluno = alunoSelecionado.getNomeAluno();
//		this.nomeAluno = alunoSelecionado.getNomeAluno();
	}
	
	public String pegarAlunoIndividual(){
		return "situacaoaluno.jsf";
	}
	
	public String editarAlunoIndividual(){
		return "editaraluno.jsf";
	}

	public void MostrarTodos(){
		alunos = new ArrayList<Aluno>();
		AlunoDAO dao = new AlunoDAO();
		alunos = dao.readAll();
	}
	
	public Boolean verificarExistencia(Aluno alu){
		AlunoDAO dao = new AlunoDAO();
		Aluno aluBD = new Aluno();
		
		List<Aluno> alunos = dao.readAll();
		if(alunos.isEmpty()) {
			return true;
		}else {
			aluBD = dao.findByCredential(alu.getMatricula());
			if(aluBD == null){
				return true;
			}else if((alu.getMatricula()).equals(aluBD.getMatricula())){
				return false;
			}
		}
		return false;
	}

	public void CadastrarNoBanco(Aluno alu){
		AlunoDAO dao = new AlunoDAO();
		dao.begin();
		dao.create(alu);
		dao.commit();
	}
	
	public void atualizarNoBanco(Aluno alu){
		AlunoDAO dao = new AlunoDAO();
		dao.begin();
		dao.update(alu);
		dao.commit();
	}
	
	public String SalvarAluno(){
		Aluno alu = new Aluno();
		alu.setDataApresentacao(this.dataApresentacao);
		alu.setMatricula(this.matricula);
		alu.setNotaAluno(this.notaAluno);
		alu.setNomeAluno(this.nomeAluno);
		alu.setTituloProjeto(this.tituloProjeto);

		Boolean ok = verificarExistencia(alu);
		if (ok){
			CadastrarNoBanco(alu);
			return "dashboard?faces-redirect=true";
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A matricula já existe!", null);
			FacesContext.getCurrentInstance().addMessage("formulario:matricula", msg);
			return null;
		}
	}
	
	public String excluirAluno(Aluno alu){
		
		AlunoDAO dao = new AlunoDAO();
		dao.begin();	
		alu = dao.read(this.getMatricula());
		
		dao.delete(alu);
		dao.commit();
		return null;
		
	}
	public String updateAluno(){
		Aluno alu = new Aluno();
		alu.setDataApresentacao(this.getDataApresentacao());
		alu.setMatricula(this.getMatricula());
		alu.setNotaAluno(this.getNotaAluno());
		alu.setNomeAluno(this.getNomeAluno());
		alu.setTituloProjeto(this.getTituloProjeto());
		

		Boolean ok = verificarExistencia(alu);
		if (!ok){
			atualizarNoBanco(alu);
			return "dashboard?faces-redirect=true";
		}else {
//			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A matricula já existe!", null);
//			FacesContext.getCurrentInstance().addMessage("editarformulario:matricula", msg);
			return null;
		}
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
	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}
	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}
//	public List<Evento> getEventos() {
//		return eventos;
//	}
//	public void setEventos(List<Evento> eventos) {
//		this.eventos = eventos;
//	}

	
	
	 
}
