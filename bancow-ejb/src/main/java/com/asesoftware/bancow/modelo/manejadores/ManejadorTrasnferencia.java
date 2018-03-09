package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.Trasnferencia;
import com.asesoftware.bancow.modelo.entidades.TrasnferenciaPK;
import javax.ejb.Stateless;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Trasnferencia.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorTrasnferencia extends ManejadorCrud<Trasnferencia,TrasnferenciaPK>{
	

    public ManejadorTrasnferencia() {
        super(Trasnferencia.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    
    // protected region Use esta region para su implementacion del manejador end        
}

