package br.edu.ifpb.pweb.tsi.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.pweb.tsi.dao.UsuarioDAO;
import br.edu.ifpb.pweb.tsi.model.Usuario;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {
	
	private String login;
    private String senha;
    private Usuario current;

    public String fazerLogin() {
    	UsuarioDAO uDAO = new UsuarioDAO();
        current = uDAO.findByLoginAndSenha(login, senha);

        if (current == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Login ou senha Invalido!"));
            return null;
        } else {
            return "dashboard";
        }
    }

    public boolean estaLogado() {
        return current != null;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
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
