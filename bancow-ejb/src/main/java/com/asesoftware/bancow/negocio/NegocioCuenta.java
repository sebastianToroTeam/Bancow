package com.asesoftware.bancow.negocio;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

import org.apache.log4j.Logger;

import com.asesoftware.bancow.modelo.entidades.Cuenta;
import com.asesoftware.bancow.modelo.manejadores.ManejadorCuenta;


import java.math.BigDecimal;

// protected region Incluya importaciones adicionales en esta seccion on begin


// protected region Incluya importaciones adicionales en esta seccion end


/**
 * Servicios para operaciones CRUD y de negocio sobre la entidad Cuenta
 * @author GeneradorCRUD
 */
@Stateless
public class NegocioCuenta extends NegocioAbstracto<Cuenta> {

    @EJB
    private ManejadorCuenta manejadorCuenta;
    
    /**
     * 
     */
    public NegocioCuenta() {
        super(Cuenta.class);        
    }
    
    @PostConstruct
    public void init() {   	
    	this.setMc(manejadorCuenta);
    }

	@Override
	protected Logger getLogger() {
		return Logger.getLogger(getClass());
	} 

    
    // protected region Use esta region para su implementacion de otros metodos on begin
    
    
    // protected region Use esta region para su implementacion de otros metodos end

}
