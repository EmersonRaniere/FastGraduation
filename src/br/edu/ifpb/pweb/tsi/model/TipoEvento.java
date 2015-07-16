package br.edu.ifpb.pweb.tsi.model;

public enum TipoEvento {
	ev_abertura_processo_colacao("Abertura Processo de Cola��o"), 
	ev_abertura_processo_estagio("Abertura Processo de Est�gio"), 
	ev_recebido_ce_pora_ctsi("Processo Recebido da Coord. de Est�gio"), 
	ev_envio_ctsi_para_professor("Processo Enviado para Prof. Orientador"), 
	ev_recebido_professor_para_ctsi("Processo Recebido do Prof. Orientador"), 
	ev_envio_ctsi_para_ce("Processo Enviado Para Coord. de Est�gio"), 
	ev_envio_ctsi_para_cca("Processo de Cola��o Enviado Para CCA"),
	ev_processo_encerrado("Processo Encerrado");
	
	private String label;
	
	private TipoEvento(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
	
}
