package br.edu.ifpb.pweb.tsi.model;

public enum TipoDefesa {
	es("Estagio"),
	pe("Pesquisa"),
	ex("Extensão");

	private String label;

    private TipoDefesa(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
