package br.edu.ifpb.pweb.tsi.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.edu.ifpb.pweb.tsi.dao.UsuarioDAO;
import br.edu.ifpb.pweb.tsi.model.Usuario;

@ManagedBean(name = "testaBean", eager=true)
@ApplicationScoped
public class TesteBean {
	
	private Usuario usuario = new Usuario("valeria.cavalcanti", "tsi123");
	
	public void init(){
		UsuarioDAO dao = new UsuarioDAO();
		dao.begin();
		dao.create(usuario);
		dao.commit();
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
