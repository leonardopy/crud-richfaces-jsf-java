package br.com.erp.ejb.enumeration;

public enum ETipoPedidoFormaPagamento {
	
	E("Entrada"), 
	S("Saída");
	
	private final String descricao;
	
	private ETipoPedidoFormaPagamento(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}