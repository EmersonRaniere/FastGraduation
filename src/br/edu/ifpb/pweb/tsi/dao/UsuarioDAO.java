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
				System.out.println("retorno > 0");
				Usuario user = (Usuario) retorno.get(0);
				return user;
			}else {
				System.out.println("retorno < 0");
				return null;
			}
			
		}catch(Exception e){
			return null;
		}
	}
	
	public Usuario changePassword(int id, String senhaNova){
		Query q = manager.createQuery("select u from Usuario u where u.id = :id");
		q.setParameter("id",id);
		
		try{
			List<Usuario> retorno = new ArrayList<Usuario>();
			retorno = q.getResultList();
			if(retorno.size() > 0){
				System.out.println("retorno > 0");
				Usuario user = (Usuario) retorno.get(0);
				
				Query p = manager.createQuery("update Usuario set senha = :senhaNova where id = :id");
				p.setParameter("senhaNova", senhaNova);
				p.setParameter("id", id);
				
				return user;
			}else {
				System.out.println("retorno < 0");
				return null;
			}
			
		}catch(Exception e){
			return null;
		}
		
	}
		
}
