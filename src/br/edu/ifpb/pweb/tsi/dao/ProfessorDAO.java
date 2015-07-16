package br.edu.ifpb.pweb.tsi.dao;

import javax.persistence.Query;

import br.edu.ifpb.pweb.tsi.model.Professor;

public class ProfessorDAO extends DAO<Professor>{

	public ProfessorDAO() {
		super();
	}
	public Professor findByCredential(String matricula){
		Query q = manager.createQuery("SELECT u FROM Professor u WHERE u.matricula = :matricula");
		q.setParameter("matricula", matricula);
		try{
			Professor p = (Professor)q.getSingleResult();
			if (p != null){
				System.out.println("entrou aqui1");
				return p;
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