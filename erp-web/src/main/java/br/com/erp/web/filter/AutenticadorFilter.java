package br.com.erp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.com.erp.ejb.enumeration.ESessionKeys;

public class AutenticadorFilter implements Filter{  
    
    public void destroy() {  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {  
          
    	HttpServletRequest req = (HttpServletRequest) request;
    			  
    	if(req.getSession().getAttribute(ESessionKeys.USUARIO.getChave())==null){
    		req.getRequestDispatcher("/login").forward(request, response);
            
    	}else{
            filter.doFilter(request, response);
    	}
    	
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
    	
    }  
}
