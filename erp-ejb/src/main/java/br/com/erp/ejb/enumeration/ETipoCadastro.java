package br.com.erp.ejb.enumeration;

public enum ETipoCadastro {
	
	CLIENTE		(0, "Cliente"), 
	FUNCIONARIO	(1, "Funcionário"), 
	FORNECEDOR	(2, "Fornecedor");
	
	private final Integer chave;
	private final String descricao;
	
	private ETipoCadastro(Integer chave, String descricao) {
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