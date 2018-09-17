package br.com.erp.ejb.enumeration;

public enum EStatusProdutoVendaFichaTecnica {
	
	NAO_BAIXA_FICHA_TECNICA(0,	"Não"),
	BAIXA_FICHA_TECNICA(1,	"Sim");
	
	private final Integer chave;
	private final String descricao;
	
	private EStatusProdutoVendaFichaTecnica(Integer chave, String descricao) {
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