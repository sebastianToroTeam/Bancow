package com.asesoftware.bancow.web.bean;

import com.asesoftware.bancow.modelo.entidades.ArchivoProcesado;
import com.asesoftware.bancow.modelo.entidades.DetDominio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpression;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionCriteria;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionOrder;
import com.asesoftware.bancow.modelo.utils.ServicioDetDominio;
import com.asesoftware.bancow.modelo.utils.UtilConstantes;
import com.asesoftware.bancow.negocio.NegocioRegistroArchivo;
import com.asesoftware.bancow.web.aplicacion.FilterService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

 
@ManagedBean
@ViewScoped
public class RegistroArchivoManagedBean implements Serializable {
     
    /**
	 * 
	 */
       
    @EJB
    private FilterService filterService;
	
	@EJB
    private NegocioRegistroArchivo negocioRegistroArchivo;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegistroArchivoManagedBean.class.getName());
	
	private RegistroArchivo registroArchivo;
	private List<RegistroArchivo> registroArchivoList;
	private List<String> fieldList;
	private List<SelectItem> fieldDomainList;
    private SearchExpression searchExpression;
    private List<SearchExpressionCriteria> searchExpressionList;
    private List<SearchExpressionOrder> searchOrderList;
    
    private String tipoProcesoSeleccionado;
    private BigDecimal filtroId;
    private Date filtroFechaIni;
    private Date filtroFechaFin;
    
     
    @PostConstruct
    public void init() {   	
    	
    	this.setFieldDomainList();
        this.resetFilter();
    	this.search();
    } 
  
    /**
     * 
     */
    public void addSearchExpressionCriteria() {
    	this.searchExpressionList.add(new SearchExpressionCriteria());
    }
    
    /**
     * 
     */
    public void addSearchOrder() {
    	this.searchOrderList.add(new SearchExpressionOrder());
    }
    /**
     * 
     * @param sec
     */
    public void removeSearchExpressionCriteria(SearchExpressionCriteria sec) {
    	this.searchExpressionList.remove(sec);
    }
    /**
     * 
     * @param seo
     */
    public void removeSearchOrder(SearchExpressionOrder seo) {
    	this.searchOrderList.remove(seo);
    }
    /**
     * 
     */
    public void search() {
    	try {	
    		if(!this.searchExpressionList.isEmpty()) {
    			this.searchExpression.setObject(UtilConstantes.TQ_CONDITIONS, this.searchExpressionList);
    		}
    		if(!this.searchOrderList.isEmpty()) {
    			this.searchExpression.setObject(UtilConstantes.TQ_ORDERING, this.searchOrderList);
    		}	
    		this.registroArchivoList = this.consultar(this.searchExpression);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda", "Mostrando "+ this.registroArchivoList.size() + " registro(s).");
            FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     */
    public void reset() {
    	this.resetFilter();
        this.search();
    }
    /**
     * 
     * @param search
     * @return
     */
    public List<RegistroArchivo> consultar(SearchExpression search) {
        return negocioRegistroArchivo.consultar(search);
    }

    /**
     * 
     */
    private void resetFilter() {
    	if (this.searchExpressionList == null || this.searchExpressionList.size()>0) {
    		this.searchExpressionList = new ArrayList<SearchExpressionCriteria>();
    	}
    	if (this.searchOrderList == null || this.searchOrderList.size()>0) {
    		this.searchOrderList = new ArrayList<SearchExpressionOrder>();
    	}
		this.searchExpression = new SearchExpression(
				new ArrayList<String>(this.filterService.getTypesafeQueryStringList()));

    }
    /**
     * 
     */
    private void setFieldDomainList() {
    	this.fieldList = new ArrayList<String>(Arrays.asList(RegistroArchivo.getAtributosEntidadRegistroArchivo()));
		this.fieldDomainList = new ArrayList<SelectItem>();
		
		// Iterar //
		this.fieldDomainList.add(new SelectItem("numero","numero"));
		this.fieldDomainList.add(new SelectItem("arcProcCodigoProceso","arcProcCodigoProceso"));
		this.fieldDomainList.add(new SelectItem("registro","registro"));
		this.fieldDomainList.add(new SelectItem("estado","estado"));
		this.fieldDomainList.add(new SelectItem("cuentaOrigen","cuentaOrigen"));
		this.fieldDomainList.add(new SelectItem("idTitularOrigen","idTitularOrigen"));
		this.fieldDomainList.add(new SelectItem("cuentaDestino","cuentaDestino"));
		this.fieldDomainList.add(new SelectItem("idTitularDestino","idTitularDestino"));
		this.fieldDomainList.add(new SelectItem("valorTransferencia","valorTransferencia"));

    }
    
    public void createRegistroArchivoWithForm() {
    }

    /**
     * 
     * @param registroArchivo
     */
    public void deleteRegistroArchivo(RegistroArchivo registroArchivo) {
	    try{
	    	negocioRegistroArchivo.delete(registroArchivo);
	    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminar RegistroArchivo", "Idenfitificado por: " +
	    		"numero: " + registroArchivo.getRegistroArchivoPK().getNumero()+ ", "+	    		"arcProcCodigoProceso: " + registroArchivo.getRegistroArchivoPK().getArcProcCodigoProceso());
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        this.search();
        }
        catch (Exception e){
	    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No fue posible eliminar el registro", "Idenfitificado por: " +
	    		"numero: " + registroArchivo.getRegistroArchivoPK().getNumero()+ ", "+	    		"arcProcCodigoProceso: " + registroArchivo.getRegistroArchivoPK().getArcProcCodigoProceso());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
 
    /**
     * 
     * @param summary
     * @param detail
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    /**
     * 
     * @param event
     */
    public void onRowEdit(RowEditEvent event) {
	    try{
	        FacesMessage msg = new FacesMessage("RegistroArchivo Editado");
	        negocioRegistroArchivo.edit((RegistroArchivo) event.getObject());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro no pudo ser editado", "Corrija los datos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    /**
     *  
     * @param event
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("RegistroArchivo Cancelado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /*
     * Getters AND Setters
     */
    public List<RegistroArchivo> getRegistroArchivoList() {
        return registroArchivoList;
    }
    
	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public List<SelectItem> getFieldDomainList() {
		return this.fieldDomainList;
	}
	
	public void setFieldDomainList(List<SelectItem> fieldDomainList) {
		this.fieldDomainList = fieldDomainList;
	}

	public List<SearchExpressionCriteria> getSearchExpressionList() {
		return searchExpressionList;
	}

	public void setSearchExpressionList(List<SearchExpressionCriteria> searchExpressionList) {
		this.searchExpressionList = searchExpressionList;
	}
	
	public List<SearchExpressionOrder> getSearchOrderList() {
		return searchOrderList;
	}

	public void setSearchOrderList(List<SearchExpressionOrder> searchOrderList) {
		System.out.println(searchOrderList);
		this.searchOrderList = searchOrderList;
	}

	public List<SelectItem> getMethods() {
		return this.filterService.getMethods();
	}
	
	public List<SelectItem> getCompoundMethods() {
		return this.filterService.getConditionsList(UtilConstantes.COMPOUND_METHODS);
	}
	
	public List<SelectItem> getOrderingList() {
		return this.filterService.getOrderingList();
	}

	public RegistroArchivo getRegistroArchivo() {
		return registroArchivo;
	}

	public void setRegistroArchivo(RegistroArchivo registroArchivo) {
		this.registroArchivo = registroArchivo;
	}
        
        public List<DetDominio> getTiposProceso() {
        return negocioRegistroArchivo.getTiposProceso();
    }

    public String getTipoProcesoSeleccionado() {
        return tipoProcesoSeleccionado;
    }

    public void setTipoProcesoSeleccionado(String tipoProcesoSeleccionado) {
        this.tipoProcesoSeleccionado = tipoProcesoSeleccionado;
    }

    public BigDecimal getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(BigDecimal filtroId) {
        this.filtroId = filtroId;
    }

    public Date getFiltroFechaIni() {
        return filtroFechaIni;
    }

    public void setFiltroFechaIni(Date filtroFechaIni) {
        this.filtroFechaIni = filtroFechaIni;
    }

    public Date getFiltroFechaFin() {
        return filtroFechaFin;
    }

    public void setFiltroFechaFin(Date filtroFechaFin) {
        this.filtroFechaFin = filtroFechaFin;
    }

    public void construirFiltros() {
        searchExpressionList.clear();
        if (tipoProcesoSeleccionado != null && !tipoProcesoSeleccionado.isEmpty()) {

            SearchExpressionCriteria tipo = new SearchExpressionCriteria();
            tipo.setCompound(UtilConstantes.CM_AND);
            tipo.setField("arcProcCodigoProceso");
            tipo.setMethod(UtilConstantes.CR_EQUAL);
            List<String> values = new ArrayList<>();
            values.add(tipoProcesoSeleccionado);
            tipo.setValues(values);

            searchExpressionList.add(tipo);
        }

        if (filtroId != null) {
            SearchExpressionCriteria fid = new SearchExpressionCriteria();
            fid.setCompound(UtilConstantes.CM_AND);
            fid.setField("codigoProceso");
            fid.setMethod(UtilConstantes.CR_EQUAL);
            List<String> values = new ArrayList<>();
            values.add(filtroId.toString());
            fid.setValues(values);

            searchExpressionList.add(fid);
        }

        if (filtroFechaIni != null) {
            SearchExpressionCriteria ffini = new SearchExpressionCriteria();
            ffini.setCompound(UtilConstantes.CM_AND);
            ffini.setField("fechaEjecucion");
            ffini.setMethod(UtilConstantes.CR_GREATER_EQUAL);
            List<String> values = new ArrayList<>();
            values.add(new SimpleDateFormat("yyyy-MM-dd").format(filtroFechaIni));
            ffini.setValues(values);

            searchExpressionList.add(ffini);
        }

        if (filtroFechaFin != null) {
            SearchExpressionCriteria fffin = new SearchExpressionCriteria();
            fffin.setCompound(UtilConstantes.CM_AND);
            fffin.setField("fechaEjecucion");
            fffin.setMethod(UtilConstantes.CR_LESS_EQUAL);
            List<String> values = new ArrayList<>();
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(filtroFechaFin) + " 23:59:59";
            values.add(fecha);
            fffin.setValues(values);

            searchExpressionList.add(fffin);
        }
    }
    
    public void mostrarMensajes(ArchivoProcesado reg) {

    }
    
    public String obtenerDescripcionPorValor(String valor){
        DetDominio dd = ServicioDetDominio.obtenerDominioPorValor(valor);
        return dd.getDescripcion();
    }
   
}