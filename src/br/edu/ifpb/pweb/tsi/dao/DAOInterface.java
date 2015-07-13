package br.edu.ifpb.pweb.tsi.dao;

public abstract interface DAOInterface<T>{
	public abstract void create(T paramT);
	public abstract T read(Object paramObject) throws Exception;

	public abstract T update(T paramT);
	  
	public abstract void delete(T paramT);
	  
	public abstract void refresh(T paramT);
}