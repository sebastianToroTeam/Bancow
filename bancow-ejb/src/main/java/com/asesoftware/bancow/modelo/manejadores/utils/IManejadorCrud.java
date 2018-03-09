package com.asesoftware.bancow.modelo.manejadores.utils;

import java.util.List;

/**
 * 
 * @author GeneradorCRUD
 * @param <T> Clase de la entidad asociada al manejador
 * @param <U> Clase del pk de la entidad T. Si es un PK compuesto es una clase con la etiqueta @Embbeded, sino una clase "primitiva" como Integer, Long, String, etc.
 */
public interface IManejadorCrud<T,U> {

    /**
     * Busca el objeto de tipo T cuyo identificar es pId
     * 
     * @param pId Identificador el objeto de clase T
     * @return El objeto de la entidad T encontrado en la busqueda
     */
    public T buscar(U pId);   

    /**
     * Crea el objeto de clase T que se pasa como parámetro en la base de datos
     * @param obj Instancia de la entidad T a almacenar en la base de datos
     */
    public void crear(T obj);

    /**
     * Crea una instancia de la clase T sin ningún valor seteado
     * @return Instancia de la entidad T sin valores asignados
     */
    public T crear();

    /**
     * Actualiza en base de datos el registro correspondiente al objeto de la entidad
     * T que se pasa como parámetro
     * @param obj Instancia de la entidad T a actualizar en la base de datos
     */
    public void actualizar(T obj);
    

    /**
     * Elimina en base de datos el registro correspondiente al objeto de la entidad
     * T que se pasa como parámetro
     * @param obj Instancia de la entidad T a eliminar de la base de datos
     */
    public void eliminar(T obj);

    /**
     * Elimina en base de datos el registro correspondiente al objeto de la entidad
     * T que se identifica con el parámetro pId
     * @param pId Instancia del identificador del objeto de la entidad T que se va a eliminar 
     * de la base de datos.
     */
    public void eliminarPorId(U pId);

    /**
     * 
     * @param search
     * @return
     */
    public List<T> consultarPorCriteria(SearchExpression search);
}