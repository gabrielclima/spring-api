package br.com.restapp.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T, I extends Serializable> {

	T save(T t);

	T find(I id);

	T merge(T t);

	void remove(T t);

	List<T> findByQueryAndNamedParams(String jpql, Map<String, ? extends Object> params);

}
