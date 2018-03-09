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

import com.asesoftware.bancow.modelo.entidades.EncDominio;
import com.asesoftware.bancow.negocio.NegocioEncDominio;
 
@ManagedBean
@ViewScoped
public class EncDominioFormManagedBean implements Serializable {

	
	@EJB
    private NegocioEncDominio negocioEncDominio;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EncDominioFormManagedBean.class.getName());
	
	private EncDominio encDominio;
    
     
    @PostConstruct
    public void init() {   	
    	this.encDominio = new EncDominio();
    }     
    /**
     * 
     */
    public void createEncDominio() {
    	try {
    		negocioEncDominio.crear(encDominio);
			FacesContext.getCurrentInstance().getExternalContext().redirect("encDominio.xhtml");
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
	public EncDominio getEncDominio() {
		return encDominio;
	}
	/**
	 * 
	 * @param encDominio
	 */
	public void setEncDominio(EncDominio encDominio) {
		this.encDominio = encDominio;
	}
   
}