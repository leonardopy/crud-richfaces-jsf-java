package br.com.erp.ejb.enumeration;

public enum ETipoTelefone {
	
	RESIDENCIAL	(0, "Residencial"), 
	CELULAR		(1, "Celular"), 
	COMERCIAL	(2, "Comercial");

	private final Integer chave;
	private final String descricao;
	
	private ETipoTelefone(Integer chave, String descricao) {
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