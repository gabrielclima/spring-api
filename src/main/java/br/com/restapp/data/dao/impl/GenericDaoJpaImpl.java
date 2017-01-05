package br.com.restapp.data.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.restapp.data.dao.GenericDao;

public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    private Class<T> type;
	
    @PersistenceContext
	protected EntityManager em;
    
    @SuppressWarnings("unchecked")
	public GenericDaoJpaImpl() {
    	ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
           this.type = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }
	
	@Override
	public T save(T t) {
		this.em.persist(t);
        return t;
	}

	@Override
	public T find(PK id) {
        return (T) this.em.find(type, id);
	}

	@Override
	public T merge(T t) {
        return this.em.merge(t);   

	}

	@Override
	public void remove(T t) {
        this.em.remove(this.em.getReference(type, t));
	}
	
}
