package br.edu.ifpb.pweb.tsi.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.dao.ProfessorDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;
import br.edu.ifpb.pweb.tsi.model.Professor;

@ManagedBean(name = "profBean")
@RequestScoped
public class ProfessorBean {
	private String matricula;
	private String nome;
	private List<Professor> professores;
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Boolean verificarExistencia(Professor prof){
		ProfessorDAO dao = new ProfessorDAO();
		Professor p = new Professor();
		
		List<Professor> professores = dao.readAll();
		if(professores.isEmpty()) {
			return true;
		}else {
			p = dao.findByCredential(p.getMatricula());
			if(p == null){
				return true;
			}else if((prof.getMatricula()).equals(p.getMatricula())){
				return false;
			}
		}
		return false;
	}
	public String pegarProfessorIndividual(){
		return "professor.jsf";
	}

	public void MostrarTodos(){
		professores = new ArrayList<Professor>();
		ProfessorDAO dao = new ProfessorDAO();
		professores = dao.readAll();
	}
	
	public void CadastrarNoBanco(Professor p){
		ProfessorDAO dao = new ProfessorDAO();
		dao.begin();
		dao.create(p);
		dao.commit();
	}
	public String SalvarProfessor(){
		Professor p = new Professor();
		p.setNome(this.nome);
		p.setMatricula(this.matricula);

		Boolean ok = verificarExistencia(p);
		if (ok){
			CadastrarNoBanco(p);
			return "professor?faces-redirect=true";
		}else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A matricula já existe!", null);
			FacesContext.getCurrentInstance().addMessage("formularioProf:matricula", msg);
			return null;
		}
	}
}
