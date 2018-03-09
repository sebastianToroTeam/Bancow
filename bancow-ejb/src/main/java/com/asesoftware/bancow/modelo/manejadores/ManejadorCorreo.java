package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.Correo;
import com.asesoftware.bancow.modelo.entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Correo.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorCorreo extends ManejadorCrud<Correo,String>{
	

    public ManejadorCorreo() {
        super(Correo.class);
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    public List<Funcionario> listafuncionarios(){
        
        List<Funcionario> lstFuncionario = new ArrayList<>();
        String sql = " SELECT p FROM Funcionario  p  LEFT JOIN Correo c  on p.numeroId = c.funcionarioNumeroId WHERE c.funcionarioNumeroId IS NULL order by p.apellidos, p.nombres ";
        
	Query q = super.mp.createQuery(sql);
        lstFuncionario = q.getResultList();       
        
        return lstFuncionario;
    }
    // protected region Use esta region para su implementacion del manejador end        
}

