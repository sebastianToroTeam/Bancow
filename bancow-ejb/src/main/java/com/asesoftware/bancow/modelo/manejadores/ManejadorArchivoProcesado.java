package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.ArchivoProcesado;
import com.asesoftware.bancow.modelo.entidades.Convenio;
import com.asesoftware.bancow.modelo.entidades.DetDominio;
import com.asesoftware.bancow.modelo.entidades.EncDominio;
import com.asesoftware.bancow.modelo.entidades.ErrorValidacion;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
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

    public boolean validarNombreArchivo(String nombreArchivo) {
        boolean existe = false;
        try {
            String sql = " SELECT COUNT(p) FROM ARCHIVO_PROCESADO  p   WHERE p.nombreArchivo ='" + nombreArchivo + "' AND P.tipoProceso ='CA' AND P.estado='CV' ";
            Query q = super.mp.createQuery(sql);
            existe = (boolean) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("com.asesoftware.bancow.modelo.manejadores.ManejadorArchivoProcesado.validarNombreArchivo()" +e.toString());
        }
        return existe;
    }
    
    public List<DetDominio> getTiposProceso(){
        EncDominio dominioTipoProceso = super.obtenerEncDominioPorCodigo(TIPO_PROCESO_DOMINIO);
        tiposProceso = obtenerValoresDominio(dominioTipoProceso);
        return tiposProceso;
    }
        public List<ErrorValidacion> obtenerErrorValidacionPorRegistro(RegistroArchivo ra){
        List<ErrorValidacion> resp = new ArrayList<>();
        
        Query q = mp.doNativeQuery("select * from ERROR_VALIDACION where ARC_PROC_CODIGO_PROCESO = ?1 AND REG_ARC_NUMERO = ?2");
        q.setParameter(1, ra.getRegistroArchivoPK().getArcProcCodigoProceso());
        q.setParameter(2, ra.getRegistroArchivoPK().getNumero());
        
        List<Object[]> objs = q.getResultList();
        for(Object[] obj : objs){
            ErrorValidacion ev = new ErrorValidacion();
            ev.setCodigo((BigDecimal)obj[0]);
            ev.setDescripcion((String)obj[1]);
            ev.setCodigoProceso((BigDecimal)obj[2]);
            ev.setNumeroRegistro((BigDecimal)obj[3]);
            resp.add(ev);
        }
        
        return resp;
    }
    // protected region Use esta region para su implementacion del manejador end        
}