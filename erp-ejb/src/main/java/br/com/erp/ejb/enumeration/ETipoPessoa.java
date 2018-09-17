package br.com.erp.ejb.enumeration;

public enum ETipoPessoa {
	
	PF("PF","Pessoa Física"), 
	PJ("PJ","Pessoa Jurídica");
	
	private final String chave;
	private final String descricao;
	
	private ETipoPessoa(String chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public String getChave() {
		return chave;
	}

	public String getDescricao() {
		return descricao;
	}
}