package com.asesoftware.bancow.web.bean;

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

import com.asesoftware.bancow.modelo.entidades.Cuenta;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpression;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionCriteria;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionOrder;
import com.asesoftware.bancow.modelo.utils.UtilConstantes;
import com.asesoftware.bancow.negocio.NegocioCuenta;
import com.asesoftware.bancow.web.aplicacion.FilterService;

 
@ManagedBean
@ViewScoped
public class CuentaManagedBean implements Serializable {
     
    /**
	 * 
	 */
       
    @EJB
    private FilterService filterService;
	
	@EJB
    private NegocioCuenta negocioCuenta;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CuentaManagedBean.class.getName());
	
	private Cuenta cuenta;
	private List<Cuenta> cuentaList;
	private List<String> fieldList;
	private List<SelectItem> fieldDomainList;
    private SearchExpression searchExpression;
    private List<SearchExpressionCriteria> searchExpressionList;
    private List<SearchExpressionOrder> searchOrderList;
    
     
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
    		this.cuentaList = this.consultar(this.searchExpression);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda", "Mostrando "+ this.cuentaList.size() + " registro(s).");
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
    public List<Cuenta> consultar(SearchExpression search) {
        return negocioCuenta.consultar(search);
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
    	this.fieldList = new ArrayList<String>(Arrays.asList(Cuenta.getAtributosEntidadCuenta()));
		this.fieldDomainList = new ArrayList<SelectItem>();
		
		// Iterar //
		this.fieldDomainList.add(new SelectItem("numero","numero"));
		this.fieldDomainList.add(new SelectItem("estado","estado"));
		this.fieldDomainList.add(new SelectItem("saldoDisponible","saldoDisponible"));
		this.fieldDomainList.add(new SelectItem("gmf","gmf"));
		this.fieldDomainList.add(new SelectItem("condicion","condicion"));
		this.fieldDomainList.add(new SelectItem("titularNumeroId","titularNumeroId"));

    }
    
    public void createCuentaWithForm() {
    }

    /**
     * 
     * @param cuenta
     */
    public void deleteCuenta(Cuenta cuenta) {
	    try{
	    	negocioCuenta.delete(cuenta);
	    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminar Cuenta", "Idenfitificado por: " + "numero: " + cuenta.getNumero());
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        this.search();
        }
        catch (Exception e){
	    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No fue posible eliminar el registro", "Idenfitificado por: " + "numero: " + cuenta.getNumero());
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
	        FacesMessage msg = new FacesMessage("Cuenta Editado");
	        negocioCuenta.edit((Cuenta) event.getObject());
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
        FacesMessage msg = new FacesMessage("Cuenta Cancelado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /*
     * Getters AND Setters
     */
    public List<Cuenta> getCuentaList() {
        return cuentaList;
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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

   
}