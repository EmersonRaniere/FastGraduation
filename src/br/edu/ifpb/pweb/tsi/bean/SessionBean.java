package br.edu.ifpb.pweb.tsi.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.dao.UsuarioDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;
import br.edu.ifpb.pweb.tsi.model.Usuario;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean {

	private Usuario usuario;
	private String senhaNovaIgual, senhaNova, senhaAntiga;

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
	public String excluirAluno(Aluno alu){
		AlunoDAO dao = new AlunoDAO();
		dao.begin();	
//		alu = dao.read(alu.getMatricula());
		dao.delete(alu);
		dao.commit();
		return null;
		
	}
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
	
	public void check(){
	    if (this.getRole() == 0) {
	        try {
//	        	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	
	public String mudarSenha(){
    	UsuarioDAO uDao = new UsuarioDAO();
    	
//    	if (current == null) {
//        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado", null);
//			FacesContext.getCurrentInstance().addMessage("formAlterarSenha:login", msg);
//			return null;
//    	}else 
    	if (!((this.senhaNova).equals(senhaNovaIgual))){
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirmação incorreta.", null);
			FacesContext.getCurrentInstance().addMessage("formAlterarSenha:login", msg);
			return null;
        } else {
        	System.out.println(senhaNova);
        	
        	Usuario u = new Usuario();
        	u.setId(this.usuario.getId());
        	u.setLogin(this.usuario.getLogin());
        	u.setRole(this.usuario.getRole());
        	u.setSenha(senhaNova);
        	this.usuario = u;
        	
        	uDao.begin();
        	uDao.update(u);
        	uDao.commit();
        	
//        	uDao.changePassword(this.usuario.getId(), senhaNova);
        	System.out.println(uDao.read(this.usuario.getId()).getSenha());
//        	this.loadFlashLogin(this.usuario);
            return "dashboard";
        }
    }
	
	

	public String pegarLogin(){
		return usuario.getLogin();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaNovaIgual() {
		return senhaNovaIgual;
	}

	public void setSenhaNovaIgual(String senhaNovaIgual) {
		this.senhaNovaIgual = senhaNovaIgual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}
	
	
	
}
