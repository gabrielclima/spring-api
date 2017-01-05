package br.com.restapp.data.dao;

import java.io.Serializable;

public interface GenericDao<T, I extends Serializable> {

	T save(T t);
	T find(I id);
	T merge(T t);
	void remove(T t);	
	
}
	