package br.com.restapp.data.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.restapp.data.HibernateFactory;
import br.com.restapp.data.dao.GenericDao;

public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    private Class<T> type;
	
	protected EntityManager em = HibernateFactory.getEntityManager();
    
	public GenericDaoJpaImpl() {
    	ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
           this.type = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }
	
	@Override
	public T save(T t) {
		this.em.getTransaction().begin();
		this.em.persist(t);
		this.em.getTransaction().commit();
        return t;
	}

	@Override
	public T find(PK id) {
        return (T) this.em.find(type, id);
	}

	@Override
	public T merge(T t) {
		this.em.getTransaction().begin();
        t = this.em.merge(t);   
        this.em.getTransaction().commit();
        return t;
	}

	@Override
	public void remove(T t) {
		this.em.getTransaction().begin();
        this.em.remove(this.em.getReference(type, t));
        this.em.getTransaction().commit();
	}
	
}
