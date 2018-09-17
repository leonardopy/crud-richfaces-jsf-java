package br.com.erp.ejb.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.erp.ejb.expection.DALException;

public class GenericBean<T extends Object, ID extends Serializable> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<T> save(List<T> lstT) throws DALException{
		if(lstT != null && !lstT.isEmpty()){
			int index = 0;
			for (T t : lstT) {
				lstT.set(index++, this.save(t));
			}
		}
		
		return lstT;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T save(T t) throws DALException{
		Class clazz = t.getClass();
		
		if(!clazz.getSuperclass().equals(Object.class)){
			clazz = clazz.getSuperclass();
		}
		
		if(clazz.isAnnotationPresent(Entity.class)){
			Field[] campos = clazz.getDeclaredFields();
			Field id = null;
			
			for (Field field : campos) {
				if(field.isAnnotationPresent(Id.class)){
					if(!field.isAccessible()){
						field.setAccessible(true);
					}
					id = field;
					break;
				}
			}
			
			try {
				if(id != null && id.get(t) == null){
					entityManager.persist(t);
				}
				else{
					entityManager.merge(t);
				}
				if(id != null){
					id.setAccessible(false);
				}
				
			} catch (IllegalArgumentException e) {
				throw new DALException(e);
			} catch (IllegalAccessException e) {
				throw new DALException(e);
			}
			
		}
		
		return t;
	}
	
	
	@SuppressWarnings("unchecked")
	public T findById(ID id){
		Class<T> clazz = ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		return (T) entityManager.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByQuery(StringBuilder hql, Object[] params){
		Query query = entityManager.createQuery(hql.toString());
		int i = 1;
		
		if(params != null){
			for (Object param : params) {
				query.setParameter(i++, param);
			}
		}
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		Class<T> clazz = ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("		t ");
		sb.append("FROM ");
		sb.append(clazz.getName());
		sb.append(" t ");
		
		return  findByQuery(sb, null);
	}
	
	@SuppressWarnings("unchecked")
	 public void removeById(ID id) throws DALException {
		try {
		  Class<T> clazz = ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		  T t = entityManager.find(clazz, id);
		  entityManager.remove(t);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
		
}
