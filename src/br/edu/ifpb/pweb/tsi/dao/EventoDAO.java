package br.edu.ifpb.pweb.tsi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.pweb.tsi.model.Evento;

public class EventoDAO extends DAO<Evento>{

	public EventoDAO() {
		super();
	}
	public List<Evento> compararEventoEAluno(String matricula){
		Query q = manager.createQuery("select u from Evento u where u.codigoAluno = :l");
		q.setParameter("l",matricula);
		try{
			List<Evento> retorno = new ArrayList<Evento>();
			retorno = q.getResultList();
			if(! (retorno.isEmpty()) ){
				System.out.println("retorno > 0");
				return retorno;
			}else {
				System.out.println("retorno < 0");
				return null;
			}
			
		}catch(Exception e){
			return null;
		}
	}
}