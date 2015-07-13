package br.edu.ifpb.pweb.tsi.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;

@ManagedBean(name = "initBean", eager=true)
@ApplicationScoped

public class InitBean {
	
	@PostConstruct
	public void init(){
		AlunoDAO test = new AlunoDAO();
		
		Aluno a1 = new Aluno("20102370111", "Emerson Raniere");
		test.begin();
		test.create(a1);
		
		test.commit();
		
		
		System.out.println(((Aluno)test.read(a1.getMatricula())).getNomeAluno());
		
	}
}
