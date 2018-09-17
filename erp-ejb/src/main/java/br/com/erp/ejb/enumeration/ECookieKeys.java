package br.com.erp.ejb.enumeration;

public enum ECookieKeys {
	
	DESTINO("destino");
	
	private final String chave;
	
	private ECookieKeys(String chave) {
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}

}