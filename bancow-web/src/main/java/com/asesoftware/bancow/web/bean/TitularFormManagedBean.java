package com.asesoftware.bancow.web.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import org.apache.log4j.Logger;

import com.asesoftware.bancow.modelo.entidades.Titular;
import com.asesoftware.bancow.negocio.NegocioTitular;
 
@ManagedBean
@ViewScoped
public class TitularFormManagedBean implements Serializable {

	
	@EJB
    private NegocioTitular negocioTitular;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TitularFormManagedBean.class.getName());
	
	private Titular titular;
    
     
    @PostConstruct
    public void init() {   	
    	this.titular = new Titular();
    }     
    /**
     * 
     */
    public void createTitular() {
    	try {
    		negocioTitular.crear(titular);
			FacesContext.getCurrentInstance().getExternalContext().redirect("titular.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro no pudo ser creado", "Corrija los datos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }

    /**
     * 
     * @return
     */
	public Titular getTitular() {
		return titular;
	}
	/**
	 * 
	 * @param titular
	 */
	public void setTitular(Titular titular) {
		this.titular = titular;
	}
   
}