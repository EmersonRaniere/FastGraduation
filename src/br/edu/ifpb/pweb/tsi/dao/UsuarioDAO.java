package br.edu.ifpb.pweb.tsi.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

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
			Usuario user = (Usuario) q.getSingleResult();
			return user;
		}catch(NoResultException e){
			return null;
		}
		
	}
}
