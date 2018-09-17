package br.com.erp.web.managedbean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.com.erp.ejb.bean.MenuBean;
import br.com.erp.ejb.entidade.Menu;
import br.com.erp.ejb.enumeration.EDestino;
import br.com.erp.ejb.enumeration.ESessionKeys;
import br.com.erp.ejb.expection.DALException;
import br.com.erp.ejb.infra.PoolString;
import br.com.erp.web.util.SessionManager;
import br.com.erp.web.util.Utils;

@Named("dashboardMBean")
@ViewScoped
public class DashboardMBean extends DefaultMBean {
	
	private static final long serialVersionUID 	= 1L;
	private static final  Logger LOGGER  		= LogManager.getLogger(DashboardMBean.class);
	
	@EJB
	private MenuBean menuBean;
	
	private List<Menu> menuSideBar;
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public String logout(){
		if(Utils.getSession()!=null){
			SessionManager.remove(ESessionKeys.USUARIO);
		}
		return EDestino.LOGIN.getDestino();
	}
	
	public String menuActive(Menu menu){
		if(menu!=null && ativaMenu(menu)){
			return PoolString.ACTIVE;
		}
		return StringUtils.EMPTY;
	}
	
	private boolean ativaMenu(Menu menu){
		String urlAtual 	= Utils.getPrettyContext().getRequestURL().toString();		
		//primeira pagina
		if(menu.getPath().substring(1, menu.getPath().length()).split(PoolString.BARRA).length==1){
			if((urlAtual.equalsIgnoreCase(menu.getPath())) || 
					menu.getPath().equalsIgnoreCase(urlAtual)){
				return true;
			}
		}else{
			if(urlAtual.equalsIgnoreCase(menu.getPath()) || 
					urlAtual.substring(0, urlAtual.lastIndexOf(PoolString.BARRA)).contains(menu.getPath())){
				return true;
			}
		}
		
		return false;
	}
	
	public List<Menu> getMenuSideBar(){
		if(menuSideBar==null){
			try {
				this.setMenuSideBar(menuBean.findMenuWithChildren(getUsuarioLogado()));
			} catch (DALException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return menuSideBar;
	}

	public void setMenuSideBar(List<Menu> menuSideBar) {
		this.menuSideBar = menuSideBar;
	}
}