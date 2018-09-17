package br.com.erp.ejb.enumeration;

public enum EStatusUsuario {
	
	INATIVO(0,"Inativo"),
	ATIVO(1,"Ativo");
	
	private final Integer chave;
	private final String descricao;
	
	private EStatusUsuario(Integer chave, String descricao) {
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