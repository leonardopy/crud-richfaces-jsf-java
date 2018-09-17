package br.com.erp.ejb.enumeration;

public enum EPerfilUsuario {
	
	ADMIN(1,"Administrador"),
	PUBLICADOR(1,"Publicador");
	
	private final Integer chave;
	private final String descricao;
	
	private EPerfilUsuario(Integer chave, String descricao) {
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