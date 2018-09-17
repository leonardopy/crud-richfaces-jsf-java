package br.com.erp.ejb.enumeration;

public enum EMes {
    
    JAN(1,"JANEIRO", "JAN"),
    FEV(2,"FEVEREIRO", "FEV"),
    MAR(3,"MAR\u00C7O", "MAR"),
    ABR(4,"ABRIL", "ABR"),
    MAI(5,"MAIO", "MAI"),
    JUN(6,"JUNHO", "JUN"),
    JUL(7,"JULHO", "JUL"),
    AGO(8,"AGOSTO", "AGO"),
    SET(9,"SETEMBRO", "SET"),
    OUT(10,"OUTUBRO", "OUT"),
    NOV(11,"NOVEMBRO", "NOV"),
    DEZ(12,"DEZEMBRO", "DEZ");
    
    private final Integer cod;
    private final String label;
    private final String abreviacao;
    
    private EMes(Integer cod, String label, String abreviacao) {
        this.label = label;
        this.cod = cod;
        this.abreviacao = abreviacao;
    }

    public String getLabel() {
            return label;
    }

    public Integer getCod() {
            return cod;
    }
    
    public static EMes getMes(Integer cod){
	    for (EMes mes : EMes.values()) {
	      if(mes.getCod().equals(cod)){
	          return mes;
	      }
	    }
       return null;
    }
    
    public static String getMesAbreviacao(Integer cod){
	    for (EMes mes : EMes.values()) {
	      if(mes.getCod().equals(cod)){
	          return mes.getAbreviacao();
	      }
	    }
       return "";
    }
    
    public static String getNomeMes(Integer cod){
	    for (EMes mes : EMes.values()) {
	      if(mes.getCod().equals(cod)){
	          return mes.getLabel();
	      }
	    }
       return "";
    }

	public String getAbreviacao() {
		return abreviacao;
	}
}
