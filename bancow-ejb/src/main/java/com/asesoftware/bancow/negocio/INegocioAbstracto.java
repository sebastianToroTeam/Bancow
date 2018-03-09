package com.asesoftware.bancow.negocio;

import java.util.List;

import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpression;

public interface INegocioAbstracto<E> {
	

	/**
     * 
     */
    public List<E> consultar(SearchExpression search);
    /**
     * 
     * @param obj
     * @return
     */
    public E crear(E obj);
    
    /**
     * 
     * @param obj
     * @return
     */
    public E edit(E obj);
    /**
     * 
     * @param obj
     */
    public void delete(E obj);

}