package br.com.erp.ejb.enumeration;

public enum EStatusProdutoVenda {
	
	NAO_VENDE	(0,"Não"),
	VENDE		(1,"Sim");
	
	private final Integer chave;
	private final String descricao;
	
	private EStatusProdutoVenda(Integer chave, String descricao) {
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