package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.Usuario;

import javax.ejb.Stateless;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Usuario.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorUsuario extends ManejadorCrud<Usuario,String>{
	

    public ManejadorUsuario() {
        super(Usuario.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    
    // protected region Use esta region para su implementacion del manejador end        
}

