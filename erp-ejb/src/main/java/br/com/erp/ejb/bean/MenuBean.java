package br.com.erp.ejb.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.erp.ejb.entidade.Menu;
import br.com.erp.ejb.entidade.Usuario;
import br.com.erp.ejb.enumeration.EStatusMenu;
import br.com.erp.ejb.expection.DALException;


@Stateless
public class MenuBean extends GenericBean<Menu, Serializable> {

	@SuppressWarnings("unchecked")
	public List<Menu> findMenuWithChildren(Usuario usuario) throws DALException {
    	Query q								= null;
    	List<Menu> menuList 				= null;
    	
    	StringBuilder sb 	= new StringBuilder("SELECT DISTINCT menu1 FROM Menu menu1 ");
    		sb.append(" LEFT JOIN FETCH menu1.menuFilhos menu2 ")
    		 .append(" WHERE menu1.tipoAcesso >= :acesso and menu2.tipoAcesso >= :acesso1 ")
    		 .append(" AND menu1.menuPai IS NULL ")
    		 .append(" AND menu1.status=:status ")
    		 .append(" ORDER BY menu1.order, menu2.order");
    		
    	try {
			q = getEntityManager().createQuery(sb.toString());
				q.setParameter("acesso", usuario.getPerfil())
					.setParameter("acesso1", usuario.getPerfil())
					.setParameter("status", EStatusMenu.ATIVO);
				
				menuList = (List<Menu>) q.getResultList();
			this.loadingMenu(menuList);
			
		}catch(Exception e){
			throw new DALException("Erro ao recuperar os itens de menu", e);
		}
		return menuList;	
	}
	
	private void loadingMenu(List<Menu> menuList) {
		if(menuList!=null){
			for (Menu menu : menuList) {
				this.loadingMenu(menu.getMenuFilhos());
			}
		}
	}
    
}