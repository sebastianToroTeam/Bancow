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

import com.asesoftware.bancow.modelo.entidades.Convenio;
import com.asesoftware.bancow.negocio.NegocioConvenio;
 
@ManagedBean
@ViewScoped
public class ConvenioFormManagedBean implements Serializable {

	
	@EJB
    private NegocioConvenio negocioConvenio;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConvenioFormManagedBean.class.getName());
	
	private Convenio convenio;
    
     
    @PostConstruct
    public void init() {   	
    	this.convenio = new Convenio();
    }     
    /**
     * 
     */
    public void createConvenio() {
    	try {
    		negocioConvenio.crear(convenio);
			FacesContext.getCurrentInstance().getExternalContext().redirect("convenio.xhtml");
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
	public Convenio getConvenio() {
		return convenio;
	}
	/**
	 * 
	 * @param convenio
	 */
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
   
}