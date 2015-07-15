package br.edu.ifpb.pweb.tsi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.pweb.tsi.model.Aluno;
import br.edu.ifpb.pweb.tsi.model.Usuario;

public class UsuarioDAO extends DAO<Usuario>{

	public UsuarioDAO(){
		super();
	}
	

	public Usuario findByLoginAndSenha(String login, String senha){
	
		Query q = manager.createQuery("select u from Usuario u where u.login = :l AND u.senha = :s");
		q.setParameter("l",login);
		q.setParameter("s",senha);
		
		try{
			List<Usuario> retorno = new ArrayList<Usuario>();
			
			retorno = q.getResultList();
			if(retorno.size() > 0){
				System.out.println("diferente de null");
				Usuario user = (Usuario) retorno.get(0);
				return user;
			}else {
				System.out.println("Igual a null");
				return null;
			}
			
		}catch(Exception e){
			return null;
		}
		
//		try{
//			Usuario u = (Usuario)q.getSingleResult();
//			if (u != null){
//				System.out.println("entrou aqui1");
//				return u;
//			}else{
//				System.out.println("entrou aqui2");
//				return null;
//			}
//		}
	}
		
}
