package com.asesoftware.bancow.negocio;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.asesoftware.bancow.modelo.entidades.Correo;
import com.asesoftware.bancow.modelo.entidades.Funcionario;
import com.asesoftware.bancow.modelo.manejadores.ManejadorCorreo;
import java.util.List;




// protected region Incluya importaciones adicionales en esta seccion end


/**
 * Servicios para operaciones CRUD y de negocio sobre la entidad Correo
 * @author GeneradorCRUD
 */
@Stateless
public class NegocioCorreo extends NegocioAbstracto<Correo> {

    @EJB
    private ManejadorCorreo manejadorCorreo;
    
    /**
     * 
     */
    public NegocioCorreo() {
        super(Correo.class);        
    }
    
    @PostConstruct
    public void init() {   	
    	this.setMc(manejadorCorreo);
    }

	@Override
	protected Logger getLogger() {
		return Logger.getLogger(getClass());
	} 

    
    // protected region Use esta region para su implementacion de otros metodos on begin
    public List<Funcionario> listafuncionarios(){
           return manejadorCorreo.listafuncionarios();
    }    
    // protected region Use esta region para su implementacion de otros metodos end

}
