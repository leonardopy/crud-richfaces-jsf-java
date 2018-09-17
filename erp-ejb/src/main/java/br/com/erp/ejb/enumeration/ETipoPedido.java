package br.com.erp.ejb.enumeration;

public enum ETipoPedido {
	
	CONTROLE_RAPIDO		(0,	"Controle Rápido",	"C"), 
	PEDIDO				(1,	"Pedido",			"P"),
	ORCAMENTO			(2,	"Orçamento",		"O");
	
	private final Integer chave;
	private final String descricao;
	private final String sigla;
	
	private ETipoPedido(Integer chave, String descricao, String sigla) {
		this.chave = chave;
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public Integer getChave() {
		return chave;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public String getSigla() {
		return sigla;
	}
}