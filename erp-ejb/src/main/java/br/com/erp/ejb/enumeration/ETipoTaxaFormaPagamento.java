package br.com.erp.ejb.enumeration;

public enum ETipoTaxaFormaPagamento {
	
	ABSOLUTO	(0, "Absoluto"), 
	PERCENTUAL	(1, "Percentual");
	
	private final Integer chave;
	private final String descricao;
	
	private ETipoTaxaFormaPagamento(Integer chave, String descricao) {
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