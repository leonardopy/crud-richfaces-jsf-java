package br.com.erp.ejb.enumeration;

public enum EStatusPedido {
	
	ABERTO				(0,	"Aberto"), 
	FINALIZADO			(1,	"Finalizado"),
	CANCELADO			(2,	"Cancelado");
	
	private final Integer chave;
	private final String descricao;
	
	private EStatusPedido(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public Integer getChave() {
		return chave;
	}
	
	public String getDescricao() {
		return descricao;
	}
}