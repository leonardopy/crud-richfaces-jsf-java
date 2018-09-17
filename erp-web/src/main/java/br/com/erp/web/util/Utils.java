package br.com.erp.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

import com.ocpsoft.pretty.PrettyContext;

import br.com.erp.ejb.enumeration.EMes;
import br.com.erp.ejb.infra.PoolString;


public class Utils {
	
	
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpServletRequest getRequest() {
		return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
	}	
	
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	public static PrettyContext getPrettyContext(){
		return PrettyContext.getCurrentInstance(Utils.getRequest());
	}
	
	public static PrettyContext getPrettyContext(HttpServletRequest req){
		return PrettyContext.getCurrentInstance(req);
	}

	public static String getURL(HttpServletRequest req){
		return getServerPath() + getPrettyContext(req).getContextPath()+getPrettyContext(req).getRequestURL().toURL();
	}
	
	public static void sendRedirect301(String url){
		sendRedirect(301, url);
	 }
	
	public static void sendRedirect302(String url){
		sendRedirect(302, url);
	 }

	public static void sendRedirect(int status, String url){
		Utils.getResponse().setStatus(status);
		Utils.getResponse().setHeader(PoolString.LOCATION,  url);
		Utils.getResponse().setHeader(PoolString.CONNECTION, PoolString.CLOSE);
    }
	
	public static String getServerProtocol() {
		   return "http://";	   
	 }
	
	public static String getServerPath() {
		String urlServer = getServerProtocol() + getRequest().getServerName();
	    if (getRequest().getLocalPort() != 80) { urlServer += ":" + getRequest().getLocalPort(); }
	    return urlServer;
	}
	
	public static String getServerContextPath() {
		return getServerPath() + getRequest().getContextPath() + PoolString.BARRA;
	}
	
	public static ServletContext getServletContext(){
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();    
	    return (ServletContext)externalContext.getContext(); 
	}	

	
	public static String formataTelefone(String str){
		try{
			MaskFormatter maskTel = new MaskFormatter("(##) ####-#####");          /* ## - Somente Numeros serão aceitos*/  
			maskTel.setValueContainsLiteralCharacters(false); 
			return maskTel.valueToString(str);
		}catch(Exception e) {
			return str;
		}
	}
	
	public static String dateToString(final Date date, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return date == null ? null : sdf.format(date);
	}
	
	public static Date stringToDate(String date, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		try {
			return date == null ? null : sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static boolean isFinalSemana(Date data){
		int dayWeek = getDiaSemana(data);		
		return dayWeek == Calendar.SUNDAY || dayWeek == Calendar.SATURDAY;		
	}
	
	public static int getDiaSemana(Date data){
		Calendar agora = Calendar.getInstance();
		agora.setTime(data);
		
		return agora.get(Calendar.DAY_OF_WEEK);
	}
	
	public static String converteHoraMin(int minutos){
		int minutosTemp = minutos;
		
		long horas = minutosTemp / 60;
		minutosTemp = minutosTemp % 60;
		if(horas==0 && minutosTemp<0){
			return String.format("-%02d:%02d", horas, Math.abs(minutosTemp));
		}
		if(horas<0){
			return String.format("-%02d:%02d", Math.abs(horas), Math.abs(minutosTemp));
		}
		return String.format("%02d:%02d", horas, Math.abs(minutosTemp));
	}
	
	public static String formataDataMesBarraAno(Date date){
		StringBuilder sb	= new StringBuilder();
		Calendar calDate	= Calendar.getInstance();
		if (date!=null){
			calDate.setTime(date);
			sb.append(EMes.getMesAbreviacao(calDate.get(Calendar.MONTH)+1));
			sb.append("/");
			sb.append(calDate.get(Calendar.YEAR));
		}
		return sb.toString();
	}

	public static String retiraFormatacao(String str){
		return str == null ? StringUtils.EMPTY : str.replaceAll("-|\\.|\\,|\\/|\\(|\\)|\\ ", "");
	}
	
	public static String retiraFormatacaoMoeda(String str){
		return str == null ? StringUtils.EMPTY : str.replaceAll("\\R|\\$|\\ |\\%", "");
	}
	
	public static String formataMoeda(BigDecimal valor){
		DecimalFormat decFormat = new DecimalFormat("'R$ ' #,###,##0.00");  
		return decFormat.format(valor);
	}
	
}