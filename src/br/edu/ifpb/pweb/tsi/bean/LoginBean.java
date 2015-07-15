package br.edu.ifpb.pweb.tsi.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.edu.ifpb.pweb.tsi.dao.UsuarioDAO;
import br.edu.ifpb.pweb.tsi.model.Usuario;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
	
	private String login;
    private String senha;
    private Usuario current;
    
    @PostConstruct
    public void init(){
    	Usuario usuario = new Usuario("valeria.cavalcanti", "tsi123", 1);
    	Usuario usuario2 = new Usuario("heremita", "tsi321", 2);
		UsuarioDAO dao = new UsuarioDAO();
		dao.begin();
		dao.create(usuario);
		dao.create(usuario2);
		dao.commit();
    }
    public String fazerLogin() {
    	UsuarioDAO uDAO = new UsuarioDAO();
        current = uDAO.findByLoginAndSenha(login, senha);
        
        if (current == null) {
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha invalido", null);
			FacesContext.getCurrentInstance().addMessage("formIndex:login", msg);
			return null;
        } else {
        	this.loadFlashLogin(current);
            return "dashboard?faces-redirect=true";
        }
    }
    
    public void loadFlashLogin(Usuario user){
    	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    	flash.put("usuario",current);
    }
    
    
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Usuario getCurrent() {
		return current;
	}
	public void setCurrent(Usuario current) {
		this.current = current;
	}
   
}
