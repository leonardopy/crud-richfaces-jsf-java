package br.com.erp.ejb.enumeration;

public enum ESessionKeys {
	
	USUARIO("usuario"),
	NUM_CONTROLE_RAPIDO("controleRapido");

	
	private final String chave;
	
	private ESessionKeys(String chave) {
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}

}