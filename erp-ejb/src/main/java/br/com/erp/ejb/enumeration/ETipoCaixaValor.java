package br.com.erp.ejb.enumeration;

public enum ETipoCaixaValor {
	
	VENDA	(0,	"Venda"), 
	TROCO	(1,	"Troco"),
	RETIRADA(2,	"Retirada"),
	ENTRADA	(3,	"Entrada");
	
	private final Integer chave;
	private final String descricao;
	
	private ETipoCaixaValor(Integer chave, String descricao) {
		this.chave		=	chave;
		this.descricao 	=	descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Integer getChave() {
		return chave;
	}
}