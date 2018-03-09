package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.Funcionario;
import java.math.BigDecimal;
import javax.ejb.Stateless;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Funcionario.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorFuncionario extends ManejadorCrud<Funcionario,BigDecimal>{
	

    public ManejadorFuncionario() {
        super(Funcionario.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    
    // protected region Use esta region para su implementacion del manejador end        
}

