package com.asesoftware.bancow.modelo.manejadores;

import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.entidades.ArchivoProcesado;
import com.asesoftware.bancow.modelo.entidades.Convenio;
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
            String sql = " SELECT COUNT(p) FROM ARCHIVO_PROCESADO  p   WHERE p.nombreArchivo ='" + nombreArchivo + "' AND P.tipoProceso ='CA' AND P.estado='CV' ";
            Query q = super.mp.createQuery(sql);
            existe = (boolean) q.getSingleResult();
        } catch (Exception e) {

        }
        return existe;
    }
    // protected region Use esta region para su implementacion del manejador end        
}
