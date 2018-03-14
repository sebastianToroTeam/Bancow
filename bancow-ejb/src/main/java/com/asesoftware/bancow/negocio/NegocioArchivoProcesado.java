package com.asesoftware.bancow.negocio;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.asesoftware.bancow.modelo.entidades.ArchivoProcesado;
import com.asesoftware.bancow.modelo.entidades.Convenio;
import com.asesoftware.bancow.modelo.entidades.ErrorValidacion;
import com.asesoftware.bancow.modelo.entidades.DetDominio;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
import com.asesoftware.bancow.modelo.manejadores.ManejadorArchivoProcesado;
import java.util.List;



// protected region Incluya importaciones adicionales en esta seccion on begin


// protected region Incluya importaciones adicionales en esta seccion end


/**
 * Servicios para operaciones CRUD y de negocio sobre la entidad ArchivoProcesado
 * @author GeneradorCRUD
 */
@Stateless
public class NegocioArchivoProcesado extends NegocioAbstracto<ArchivoProcesado> {

    @EJB
    private ManejadorArchivoProcesado manejadorArchivoProcesado;
    
    /**
     * 
     */
    public NegocioArchivoProcesado() {
        super(ArchivoProcesado.class);        
    }
    
    @PostConstruct
    public void init() {   	
    	this.setMc(manejadorArchivoProcesado);
    }

	@Override
	protected Logger getLogger() {
		return Logger.getLogger(getClass());
	} 

    
    // protected region Use esta region para su implementacion de otros metodos on begin
    
    public List<Convenio> listaConvenios(){
           return manejadorArchivoProcesado.listaConvenios();
    }
    
    public boolean validarNombreArchivo(String nombreArchivo){
        return manejadorArchivoProcesado.validarNombreArchivo(nombreArchivo);
    }
    public boolean registrarErrorValidacion(ErrorValidacion error){
        return this.mc.registrarErrorValidacion(error);    
    }
    
    public List<DetDominio> getTiposProceso(){
        return manejadorArchivoProcesado.getTiposProceso();
    }
     public List<ErrorValidacion> obtenerErrorValidacionPorRegistro(RegistroArchivo ra){
        return manejadorArchivoProcesado.obtenerErrorValidacionPorRegistro(ra);
    }     
     
     @Override
     public ArchivoProcesado crear(ArchivoProcesado obj) {
        // protected region Modifique el metodo crear on begin

        logService(this.getClass().getName(), "crear", obj);
        manejadorArchivoProcesado.crear(obj);
        return obj;
    }
    // protected region Use esta region para su implementacion de otros metodos end
 }
