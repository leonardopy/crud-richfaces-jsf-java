package br.com.erp.ejb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.erp.ejb.entidade.Blog;
import br.com.erp.ejb.expection.DALException;
import br.com.erp.ejb.infra.PoolString;
import br.com.erp.ejb.util.Utils;


@Stateless
public class BlogBean extends GenericBean<Blog, Serializable> {
	
    public Blog findByCod(Long idBlog) throws DALException{
	    Query q				= null;
    	StringBuilder sb 	= new StringBuilder("SELECT b FROM Blog b ")
    		 .append(" WHERE b.idBlog =:idBlog ");
    	try {
			q = getEntityManager().createQuery(sb.toString());
				q.setParameter("idBlog", idBlog);
			return (Blog)q.setMaxResults(1).getSingleResult();
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new DALException(e);
		}
    }

	public List<Blog> pesquisar(Blog blogPesq)  throws DALException{
    	StringBuilder sb 			=	new StringBuilder("SELECT b FROM Blog b ");
    	List<Object> params			=	new ArrayList<Object>();	
    	 
		if(!Utils.isBlankOrNull(blogPesq.getTitulo())){
    		sb.append(Utils.inserirWhereOuAnd(sb.toString()));
    		sb.append("UPPER(b.titulo) LIKE ? ");
    		params.add(PoolString.PERCENT.concat(blogPesq.getTitulo().toUpperCase()).concat(PoolString.PERCENT));
		}
		
		if(!Utils.isBlankOrNull(blogPesq.getUsuario().getLogin())){
    		sb.append(Utils.inserirWhereOuAnd(sb.toString()));
    		sb.append("UPPER(b.usuario.login) = ? ");
    		params.add(blogPesq.getUsuario().getLogin());
		}
		
		    		
    	return  findByQuery(sb, params.toArray());
	}
	
}