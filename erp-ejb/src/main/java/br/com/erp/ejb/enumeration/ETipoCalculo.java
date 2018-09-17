package br.com.erp.ejb.enumeration;

public enum ETipoCalculo {
	
	ABSOLUTO	(0, "Absoluto"), 
	PERCENTUAL	(1, "Percentual");
	
	private final Integer chave;
	private final String descricao;
	
	private ETipoCalculo(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public Integer getChave() {
		return chave;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static ETipoCalculo getEnumByname(String nome) {
		for (ETipoCalculo a : values()) {
			if (a.name().equals(nome)) {
				return a;
			}
		}
		return null;
	}
	
}