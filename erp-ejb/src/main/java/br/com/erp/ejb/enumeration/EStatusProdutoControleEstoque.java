package br.com.erp.ejb.enumeration;

public enum EStatusProdutoControleEstoque {
	
	
	NAO_CONTROLA_ESTOQUE(0,	"Não"),
	CONTROLE_ESTOQUE(1,	"Sim");
	
	private final Integer chave;
	private final String descricao;
	
	private EStatusProdutoControleEstoque(Integer chave, String descricao) {
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