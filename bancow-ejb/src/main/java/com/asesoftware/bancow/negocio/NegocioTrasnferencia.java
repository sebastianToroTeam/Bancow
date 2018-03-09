package com.asesoftware.bancow.negocio;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

import org.apache.log4j.Logger;

import com.asesoftware.bancow.modelo.entidades.Trasnferencia;
import com.asesoftware.bancow.modelo.manejadores.ManejadorTrasnferencia;


import java.math.BigDecimal;
import java.sql.Timestamp;

// protected region Incluya importaciones adicionales en esta seccion on begin


// protected region Incluya importaciones adicionales en esta seccion end


/**
 * Servicios para operaciones CRUD y de negocio sobre la entidad Trasnferencia
 * @author GeneradorCRUD
 */
@Stateless
public class NegocioTrasnferencia extends NegocioAbstracto<Trasnferencia> {

    @EJB
    private ManejadorTrasnferencia manejadorTrasnferencia;
    
    /**
     * 
     */
    public NegocioTrasnferencia() {
        super(Trasnferencia.class);        
    }
    
    @PostConstruct
    public void init() {   	
    	this.setMc(manejadorTrasnferencia);
    }

	@Override
	protected Logger getLogger() {
		return Logger.getLogger(getClass());
	} 

    
    // protected region Use esta region para su implementacion de otros metodos on begin
    
    
    // protected region Use esta region para su implementacion de otros metodos end

}
