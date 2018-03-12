package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.entidades.DetDominio;
import com.asesoftware.bancow.modelo.entidades.EncDominio;
import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivoPK;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad RegistroArchivo.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorRegistroArchivo extends ManejadorCrud<RegistroArchivo,RegistroArchivoPK>{
	
    private static final String TIPO_PROCESO_DOMINIO = "TIPO_PRO";
    private List<DetDominio> tiposProceso;

    public ManejadorRegistroArchivo() {
        super(RegistroArchivo.class);
        
    }   
    
    // protected region Use esta region para su implementacion del manejador on begin 
    public List<DetDominio> getTiposProceso(){
        EncDominio dominioTipoProceso = super.obtenerEncDominioPorCodigo(TIPO_PROCESO_DOMINIO);
        tiposProceso = obtenerValoresDominio(dominioTipoProceso);
        return tiposProceso;
    }
    // protected region Use esta region para su implementacion del manejador end   
    
    

}

