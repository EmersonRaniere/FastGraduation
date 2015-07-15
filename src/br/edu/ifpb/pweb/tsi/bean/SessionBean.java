package br.edu.ifpb.pweb.tsi.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.edu.ifpb.pweb.tsi.model.Usuario;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean {

	private Usuario usuario;

	@PostConstruct
	public void unloadFlashLogin() {
		System.out.println("unloaflash executado");
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();

		if (this.usuario == null)
			this.usuario = (Usuario) flash.get("usuario");
	}

	public boolean estaLogado() {
		return usuario != null;
	}
	
	public int getRole(){
		if(this.estaLogado()){
			return usuario.getRole();
		}
		else{
			 FacesContext.getCurrentInstance().addMessage(null, 
		                new FacesMessage("Acesso Negado!"));
			return 0;
		}
	}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
	
	public void check(){
	    if (this.getRole() == 0) {
	        try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
