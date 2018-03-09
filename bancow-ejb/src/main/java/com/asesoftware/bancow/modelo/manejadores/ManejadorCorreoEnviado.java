package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.CorreoEnviado;
import com.asesoftware.bancow.modelo.entidades.CorreoEnviadoPK;
import javax.ejb.Stateless;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad CorreoEnviado.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorCorreoEnviado extends ManejadorCrud<CorreoEnviado,CorreoEnviadoPK>{
	

    public ManejadorCorreoEnviado() {
        super(CorreoEnviado.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    
    // protected region Use esta region para su implementacion del manejador end        
}

