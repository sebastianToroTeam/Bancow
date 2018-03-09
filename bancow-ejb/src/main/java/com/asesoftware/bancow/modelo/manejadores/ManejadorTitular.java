package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.Titular;
import java.math.BigDecimal;
import javax.ejb.Stateless;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Titular.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorTitular extends ManejadorCrud<Titular,BigDecimal>{
	

    public ManejadorTitular() {
        super(Titular.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    
    // protected region Use esta region para su implementacion del manejador end        
}

