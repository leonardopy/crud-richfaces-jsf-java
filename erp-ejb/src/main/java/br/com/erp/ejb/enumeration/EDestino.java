package br.com.erp.ejb.enumeration;

public enum EDestino {
	
	DASHBOARD("pretty:dashboard","Página inicial", "dashboard"),
	LOGIN("pretty:login","Login", "login");
	
	
	private final String destino;
	private final String descricao;
	private final String idPretty;

	
	private EDestino(String destino, String descricao, String idPretty) {
		this.destino 	= destino;
		this.descricao 	= descricao;
		this.idPretty 		= idPretty;
	}

	public String getDestino() {
		return destino;
	}

	public String getDescricao() {
		return descricao;
	}
	public String getIdPretty() {
		return idPretty;
	}
	
	public static EDestino getEnumByname(String nome) {
		for (EDestino a : values()) {
			if (a.name().equals(nome)) {
				return a;
			}
		}
		return null;
	}
	
}