package br.edu.ifpb.pweb.tsi.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.jpa.criteria.predicate.IsEmptyPredicate;

import br.edu.ifpb.pweb.tsi.dao.AlunoDAO;
import br.edu.ifpb.pweb.tsi.dao.EventoDAO;
import br.edu.ifpb.pweb.tsi.model.Aluno;
import br.edu.ifpb.pweb.tsi.model.Evento;
import br.edu.ifpb.pweb.tsi.model.TipoDefesa;
import br.edu.ifpb.pweb.tsi.model.TipoEvento;

@ManagedBean(name = "eventoBean")
@SessionScoped
public class EventoBean {

	private Aluno alu;
	private Date dataEvento;
	private TipoEvento tipo;
	private boolean encerrado;
	
	@PostConstruct
	public void addAluno(){
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String matriculaAluno = params.get("matricula");
		
		AlunoDAO dao = new AlunoDAO();
		this.alu = dao.findByCredential(matriculaAluno);
		
		System.out.println("selecionado " + matriculaAluno);
	}
	
	public void salvarEvento() {
		Evento ev = new Evento();
		
		ev.setCodigoAluno(this.alu);
		ev.setDataEvento(this.dataEvento);
		ev.setTipo(this.tipo);
		ev.setencerrado(this.encerrado);

		EventoDAO dao = new EventoDAO();
		dao.begin();
		dao.create(ev);
		
		
		System.out.println(dao.read(ev.getId()));
		dao.commit();
	}

	public void editarEvento() {

	}

	public void excluirEvento() {

	}

	public String selecionaTipo() {
		
		List<Evento> eventos = alu.getEventos();
		
		if(!eventos.isEmpty()){
			
			this.tipo = eventos.get(eventos.size()-1).getTipo();
			
			switch (this.tipo) {
			case ev_abertura_processo_colacao:
				this.tipo = TipoEvento.ev_abertura_processo_estagio;
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
				this.encerrado = false;
				this.encerrado = true;
				break;
	
			default:
				this.tipo = TipoEvento.ev_abertura_processo_colacao;
				this.encerrado = false;
				break;
			}
		}
		else{
			this.tipo = TipoEvento.ev_abertura_processo_colacao;
		}
		return this.tipo.getLabel();
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

	public void setencErrado(boolean encerrado) {
		this.encerrado = encerrado;
	}

}
