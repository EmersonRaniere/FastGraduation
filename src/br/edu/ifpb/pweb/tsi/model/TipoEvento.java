package br.edu.ifpb.pweb.tsi.model;

public enum TipoEvento {
	ev_abertura_processo_colacao("Abertura_Processo_de_Colação"), 
	ev_abertura_processo_estagio("Abertura_Processo_de_Estágio"), 
	ev_recebido_ce_pora_ctsi("Processo_Recebido_da_Coord_de_Estágio"), 
	ev_envio_ctsi_para_professor("Processo_Enviado_para_Prof_Orientador"), 
	ev_recebido_professor_para_ctsi("Processo_Recebido_do_Prof_Orientador"), 
	ev_envio_ctsi_para_ce("Processo_Enviado_Para_Coord-de_Estágio"), 
	ev_envio_ctsi_para_cca("Processo_de_Colação_Enviado_Para_CCA"),
	ev_processo_encerrado("Processo_Encerrado");
	
	private String label;
	
	private TipoEvento(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
	
	
}
