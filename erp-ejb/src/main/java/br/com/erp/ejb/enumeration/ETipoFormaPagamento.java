package br.com.erp.ejb.enumeration;

public enum ETipoFormaPagamento {
	
	E("Entrada"), 
	S("Saída"),
	A("Ambos");
	
	private final String descricao;
	
	private ETipoFormaPagamento(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}