package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivoPK;
import javax.ejb.Stateless;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad RegistroArchivo.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorRegistroArchivo extends ManejadorCrud<RegistroArchivo,RegistroArchivoPK>{
	

    public ManejadorRegistroArchivo() {
        super(RegistroArchivo.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    
    // protected region Use esta region para su implementacion del manejador end        
}

