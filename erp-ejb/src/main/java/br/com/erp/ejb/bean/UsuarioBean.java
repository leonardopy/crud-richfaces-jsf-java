package br.com.erp.ejb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.erp.ejb.entidade.Usuario;
import br.com.erp.ejb.enumeration.EStatusUsuario;
import br.com.erp.ejb.expection.DALException;
import br.com.erp.ejb.infra.PoolString;
import br.com.erp.ejb.util.Utils;


@Stateless
public class UsuarioBean extends GenericBean<Usuario, Serializable> {

    public Usuario getUsuarioLogin(String login, String senha) throws DALException {
    	
    	Query q				= null;
    	Usuario usuario 	= null;
    	StringBuilder sb 	= new StringBuilder("SELECT u FROM Usuario u ");
    		sb.append(" WHERE ")
    		 .append(" u.login=:login ")
    		 .append(" AND u.senha=:senha ")
    		 .append(" AND u.status=:statusUsuario ");
    		
    	try {
			q = getEntityManager().createQuery(sb.toString());
				q.setParameter("login", login)
				 .setParameter("senha",senha)
				 .setParameter("statusUsuario", EStatusUsuario.ATIVO);

			
			usuario = (Usuario) q.setMaxResults(1).getSingleResult();

		}catch(NoResultException e){
			return null;
			
		}catch(Exception e){
			throw new DALException("Erro ao recuperar o Usuário", e);
		}
		return usuario;
    }
    
    
    public List<Usuario> pesquisar(Usuario u)  throws DALException{
    	StringBuilder sb 			=	new StringBuilder("SELECT u FROM Usuario u ");
    	List<Object> params			=	new ArrayList<Object>();	
    	 
		if(!Utils.isBlankOrNull(u.getLogin())){
    		sb.append(Utils.inserirWhereOuAnd(sb.toString()));
    		sb.append("UPPER(u.login) LIKE ? ");
    		params.add(PoolString.PERCENT.concat(u.getLogin().toUpperCase()).concat(PoolString.PERCENT));
		}
		    		
    	return  findByQuery(sb, params.toArray());
	}
    
}