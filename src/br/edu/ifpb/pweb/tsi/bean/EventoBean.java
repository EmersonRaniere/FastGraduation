package br.edu.ifpb.pweb.tsi.bean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;





import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.dao.EventoDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;
import br.edu.ifpb.pweb.tsi.model.Evento;
import br.edu.ifpb.pweb.tsi.model.TipoEvento;

@ManagedBean(name = "eventoBean")
@ViewScoped
public class EventoBean {

	private Aluno alu;
	private Date dataEvento;
	private TipoEvento tipo;
	private boolean encerrado;
	private int fim;
	
	@PostConstruct
	public void addAluno(){
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String matriculaAluno = params.get("matricula");
		
		AlunoDAO dao = new AlunoDAO();
		this.alu = dao.findByCredential(matriculaAluno);
		
		if(this.selecionaTipo().equals("Processo_Encerrado")){
			this.fim = 1;
		}
	}
	
	public void salvarEvento() throws UnsupportedEncodingException {
		Evento ev = new Evento();
		
		ev.setCodigoAluno(this.alu.getMatricula());
		ev.setDataEvento(this.dataEvento);
		ev.setTipo(this.tipo);

		EventoDAO dao = new EventoDAO();
		dao.begin();
		dao.create(ev);
		dao.commit();
		

		this.alu.addEvento(ev);
		
		AlunoDAO aludao = new AlunoDAO();
		aludao.begin();
		
		Aluno temp = new Aluno();
		temp = (aludao.read(alu.getMatricula()));
		if ((temp != null) && ((temp.getMatricula()) == ev.getCodigoAluno() )){
			aludao.update(this.alu);
			aludao.commit();
		}else {
			aludao.commit();
		}
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String url = ec.getRequestContextPath() + "/situacaoaluno.jsf?matricula=" + URLEncoder.encode(alu.getMatricula(), "UTF-8");
	    try {
			ec.redirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return "situacaoaluno.jsf?faces-redirect=true&include?matricula=" + alu.getMatricula();
	}

	public void editarEvento() {

	}

	public void excluirEvento() {

	}

	public String selecionaTipo() {
		
		List<Evento> eventos = this.pegaEventos();
		
		if(!eventos.isEmpty()){
			this.tipo = eventos.get(eventos.size()-1).getTipo();
			
			switch (this.tipo) {
			case ev_abertura_processo_colacao:
				this.tipo = TipoEvento.ev_abertura_processo_estagio;
				this.fim = 0;
				this.encerrado = false;
				break;
	
			case ev_abertura_processo_estagio:
				this.tipo = TipoEvento.ev_recebido_ce_pora_ctsi;
				this.encerrado = false;
				break;
	
			case ev_recebido_ce_pora_ctsi:
				this.tipo = TipoEvento.ev_envio_ctsi_para_professor;
				this.encerrado = false;
				break;
	
			case ev_envio_ctsi_para_professor:
				this.tipo = TipoEvento.ev_recebido_professor_para_ctsi;
				this.encerrado = false;
				break;
	
			case ev_recebido_professor_para_ctsi:
				this.tipo = TipoEvento.ev_envio_ctsi_para_ce;
				this.encerrado = false;
				break;
	
			case ev_envio_ctsi_para_ce:
				this.tipo = TipoEvento.ev_envio_ctsi_para_cca;
				this.encerrado = false;
				break;
				
			case ev_envio_ctsi_para_cca:
				this.tipo = TipoEvento.ev_processo_encerrado;
				this.fim = 1;
				this.encerrado = true;
				break;
			}
		}
		else{
			this.tipo = TipoEvento.ev_abertura_processo_colacao;
		}
		return this.tipo.getLabel();
	}
	

	public List<Evento> pegaEventos(){
		return this.alu.getEventos();
	}

	public Aluno getAlu() {
		return alu;
	}

	public void setAlu(Aluno alu) {
		this.alu = alu;
	}

	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public TipoEvento[] getTipo() {
		return tipo.values();
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public boolean isEncerrado() {
		return encerrado;
	}
	
	public boolean getEncerrado(){
		return encerrado;
	}

	public void setencErrado(boolean encerrado) {
		this.encerrado = encerrado;
	}

	public int getFim() {
		return fim;
	}

	public void setFim(int fim) {
		this.fim = fim;
	}
	
	

}
