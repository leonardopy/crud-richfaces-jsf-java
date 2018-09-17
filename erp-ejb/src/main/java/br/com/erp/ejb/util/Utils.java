package br.com.erp.ejb.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import br.com.erp.ejb.expection.ErpException;
import br.com.erp.ejb.infra.PoolString;

public class Utils {
	
	public static final String PATTERN_DATA_HORA 	=	"dd/MM/yyyy HH:mm:ss";
	public static final String PATTERN_DATA 		=	"dd/MM/yyyy";
	public static final String PATTERN_HORA_MIN 	=	"HH:mm";
	public static final String PATTERN_MES_ANO 		=	"MM/yyyy";
	public static final String PATTERN_DIA_MES_ANO 	=	"dd/MM/yyyy";
	public static final String HORA_MINUTO 			=	"HHH:mm";
	public static final String PATTERN_DATA_HORA2 	=	"yyyy/MM/dd HH:mm:ss";
	public static final String EMPTY				=	"";


	private Utils(){};
	
	public static String formataMesHifenAno(Date date){
		StringBuilder sb	= new StringBuilder();
		Calendar calDate	= Calendar.getInstance();
		if (date!=null){
			calDate.setTime(date);
			sb.append(String.format("%02d", calDate.get(Calendar.MONTH)+1)).append("-").append(calDate.get(Calendar.YEAR));
		}
		return sb.toString();
	}
	
	public static Properties getProperties(String nome){
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(nome));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			properties = null;
		}
		return properties;
	}
		
	
	public static <E> List<E> toList(Set<E> s) {
        if(s == null){
        	return new ArrayList<E>();
        }
		return new ArrayList<E>(s);
    }
	
	public static void appendPeriodo(String periodoDe, String periodoAte, StringBuilder sb, List<Object> parametros, String data) {
		
		String dia = "01/";
		
		if (!Utils.isBlankOrNull(periodoDe) && !Utils.isBlankOrNull(periodoAte)){	
 			sb.append(Utils.inserirWhereOuAnd(sb.toString()))
 			.append(data)
			.append(" >= to_date( ? ,'dd/mm/yyyy') AND ")
			.append(data)
			.append(" <= to_date( ? ,'dd/mm/yyyy') ");
			
			parametros.add(dia+periodoDe);
			parametros.add(dia+periodoAte);
		} else {		
			if(!Utils.isBlankOrNull(periodoDe)){
	 			sb.append(Utils.inserirWhereOuAnd(sb.toString()))
				.append(data)
				.append(" >= to_date( ? ,'dd/mm/yyyy')");
				
				parametros.add(dia+periodoDe);
			}
			if(!Utils.isBlankOrNull(periodoAte)){
	 			sb.append(Utils.inserirWhereOuAnd(sb.toString()))
				.append(data)
				.append(" <= to_date( ? ,'dd/mm/yyyy')");
				
				parametros.add(dia+periodoAte);
			}
	 	}
	}
	
	public static boolean isBlankOrNull(final String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }
	
	public static String validarPeriodo(String periodoDe, String periodoAte){
		int mesDe = 0;
		int anoDe = 0;
		int mesAte = 0;
		int anoAte = 0;
		
		String mensagem = null;
		
		if(!isBlankOrNull(periodoDe) || !isBlankOrNull(periodoAte)){
			
			if(!isBlankOrNull(periodoDe) && periodoDe.length() != 7){
				mensagem = "javax.faces.converter.DateTimeConverter.DATE";
				return mensagem;
			}
			if(!isBlankOrNull(periodoAte) && periodoAte.length() != 7){
				mensagem = "javax.faces.converter.DateTimeConverter.DATE";
				return mensagem;
			}
			
			if(!isBlankOrNull(periodoDe) && !isBlankOrNull(periodoAte)){
				mesDe = Integer.parseInt(periodoDe.substring(0, 2));
				anoDe = Integer.parseInt(periodoDe.substring(3, 7));
				
				mesAte = Integer.parseInt(periodoAte.substring(0, 2));
				anoAte = Integer.parseInt(periodoAte.substring(3, 7));
				
				if(anoDe > anoAte){
					mensagem = "erro.dataInicioMaiorFim";
				}else{
					if(anoDe == anoAte && mesDe > mesAte){
						mensagem = "erro.dataInicioMaiorFim";
					}
				}
			}
		}
		return mensagem;
	}

	public static String inserirWhereOuAnd(String query) {
		return (query.contains(" WHERE ")) ? " AND " : " WHERE ";
	}
	
	public static String replaceQuebraLinha(String str){
		if(str!=null){
			return str.replaceAll("\n", "<br>");
		}
		return str;
	}
	
    public static String convertStringToMD5(String password) throws ErpException {
        MessageDigest md;
		try {
			md = MessageDigest.getInstance(PoolString.MD5);
		} catch (NoSuchAlgorithmException e) {
			throw new ErpException("Erro ao criptografar MD5",e);
		}
        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
        return String.format("%32x", hash);
    }
}