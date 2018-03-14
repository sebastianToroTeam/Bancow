/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoftware.bancow.negocio;

import java.util.List;

import javax.ejb.EJB;

import org.apache.log4j.Logger;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpression;

/**
 * Clase abstracta con las operaciones principales utilizadas en el CRUD de los
 * servicios REST.
 *
 * @author GeneradorCRUD
 */
/**
 * Clase abstracta con las operaciones principales utilizadas en el CRUD de los
 * servicios REST.
 *
 * @author GeneradorCRUD
 *
 * @param <E> La entidad sobre la cual se realizan las operaciones CRUD o
 * operaciones de negocio
 * @param <D> El DAO asociado a la entidad E que se utiliza para transmitir su
 * información por medio de http
 */
public abstract class NegocioAbstracto<E> implements INegocioAbstracto<E> {

    protected ManejadorCrud mc;

    private Class<E> claseEntidad;

    /**
     * Devuelve el logger del servicio que extiende está clase.
     *
     * @return Logger
     */
    protected abstract Logger getLogger();

    /**
     * Imprime en el log el llamado al servicio que se pasa como parámetro,
     * imprimiendo cada uno de los parámetros que recibe.
     *
     * @param className Nombre de la clase que contiene al servicio
     * @param nombreServicio Nombre del servicio
     * @param parameters Parámetros que recibe el servicio.
     */
    /**
     *
     * @param claseEntidad
     */
    public NegocioAbstracto(Class<E> claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    public void logService(String className, String nombreServicio, Object... parameters) {
        getLogger().debug(className + "." + nombreServicio + " --> Inicio");
        for (int i = 0; i < parameters.length; i++) {
            getLogger().debug(className + "." + nombreServicio + " --> parametro +" + i + ": " + parameters[i]);
        }
    }

    /**
     *
     */
    public List<E> consultar(SearchExpression search) {

        return mc.consultarPorCriteria(search);
    }

    /**
     *
     * @param E
     * @return
     */
    public E crear(E obj) {
        // protected region Modifique el metodo crear on begin

        logService(this.getClass().getName(), "crear", obj);
        mc.crear(obj);
        return obj;
    }

    public E edit(E obj) {
        // protected region Modifique el metodo actualizar on begin

        logService(this.getClass().getName(), "actualizar", obj);
        mc.actualizar(obj);

        return obj;
        // protected region Modifique el metodo actualizar end
    }

    /**
     *
     * @param obj
     */
    public void delete(E obj) {
        mc.eliminar(obj);
    }

    public ManejadorCrud getMc() {
        return mc;
    }

    public void setMc(ManejadorCrud mc) {
        this.mc = mc;
    }

}
