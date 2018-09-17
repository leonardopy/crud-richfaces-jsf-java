package br.com.erp.ejb.enumeration;

public enum EEstadoCivil {
	
	SOLTEIRO		(0,"Solteiro"), 
	CASADO			(1,"Casado"),
	DIVORCIADO		(2,"Divorciado"), 
	VIUVO			(3,"Viúvo");
	
	private final Integer chave;
	private final String descricao;
	
	private EEstadoCivil(Integer chave, String descricao) {
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