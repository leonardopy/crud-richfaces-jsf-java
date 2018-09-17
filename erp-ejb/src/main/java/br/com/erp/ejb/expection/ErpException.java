package br.com.erp.ejb.expection;

public class ErpException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErpException(String string) {
		super(string);
	}
	
	public ErpException(String string, Throwable cause) {
		super(string, cause);
	}

	public ErpException(Exception e) {
		super(e);
	}
}
