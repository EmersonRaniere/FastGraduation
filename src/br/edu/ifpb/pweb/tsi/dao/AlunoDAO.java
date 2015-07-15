package br.edu.ifpb.pweb.tsi.dao;

import javax.persistence.Query;

import br.edu.ifpb.pweb.tsi.model.Aluno;

public class AlunoDAO extends DAO<Aluno>{

	public AlunoDAO() {
		super();
	}
	public Aluno findByCredential(String matricula){
		Query q = manager.createQuery("SELECT u FROM Aluno u WHERE u.matricula = :matricula");
		q.setParameter("matricula", matricula);
		try{
			Aluno u = (Aluno)q.getSingleResult();
			if (u != null){
				System.out.println("entrou aqui1");
				return u;
			}else{
				System.out.println("entrou aqui2");
				return null;
			}
		}
		catch (Exception e) {
			return null;
		}
	}
}