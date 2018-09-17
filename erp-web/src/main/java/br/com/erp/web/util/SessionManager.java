 package br.com.erp.web.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.erp.ejb.enumeration.ESessionKeys;


public abstract class SessionManager {
	
	public static void putAttribute(ESessionKeys key, Object value){
		getSession().setAttribute(key.getChave(), value);
	}
	
	public static Object restoreAttribute(ESessionKeys key){
		return getSession().getAttribute(key.getChave());
	}
	
	public static void remove(ESessionKeys key){
		getSession().removeAttribute(key.getChave());
	}
	
	public static Object restoreAttributeAndRemove(ESessionKeys key){
		Object ret = getSession().getAttribute(key.getChave());
		remove(key);
		return ret;
	}
	
	private static HttpSession getSession() {
		return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
	}
}