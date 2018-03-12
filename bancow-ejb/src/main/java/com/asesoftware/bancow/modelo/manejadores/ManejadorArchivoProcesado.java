package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.ArchivoProcesado;
import com.asesoftware.bancow.modelo.entidades.Convenio;
import com.asesoftware.bancow.modelo.entidades.DetDominio;
import com.asesoftware.bancow.modelo.entidades.EncDominio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad ArchivoProcesado.
 *
 * @author GeneradorCRUD
 */
@Stateless
public class ManejadorArchivoProcesado extends ManejadorCrud<ArchivoProcesado, BigDecimal> {
    private static final String TIPO_PROCESO_DOMINIO = "TIPO_PRO";
    private List<DetDominio> tiposProceso;

    public ManejadorArchivoProcesado() {
        super(ArchivoProcesado.class);
    }

    // protected region Use esta region para su implementacion del manejador on begin 
    public List<Convenio> listaConvenios() {

        List<Convenio> lstConvenio = new ArrayList<>();
        String sql = " SELECT p FROM Convenio  p   WHERE p.estado  = 'I'  order by p.nombre ";

        Query q = super.mp.createQuery(sql);
        lstConvenio = q.getResultList();

        return lstConvenio;
    }

    public boolean ValidarNombreArchivo(String nombreArchivo) {
        boolean existe = false;
        try {
            String sql = " SELECT COUNT(p) FROM ARCHIVO_PROCESADO  p   WHERE p.nombreArchivo ='" + nombreArchivo + "'";
            Query q = super.mp.createQuery(sql);
            existe = (boolean) q.getSingleResult();
        } catch (Exception e) {

        }
        return existe;
    }
    
    public List<DetDominio> getTiposProceso(){
        EncDominio dominioTipoProceso = super.obtenerEncDominioPorCodigo(TIPO_PROCESO_DOMINIO);
        tiposProceso = obtenerValoresDominio(dominioTipoProceso);
        return tiposProceso;
    }
    // protected region Use esta region para su implementacion del manejador end        
}
