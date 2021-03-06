package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.entidades.DetDominio;
import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.EncDominio;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad EncDominio.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorEncDominio extends ManejadorCrud<EncDominio,String>{
	

    public ManejadorEncDominio() {
        super(EncDominio.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    
    // protected region Use esta region para su implementacion del manejador end    

    
}

