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

import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
import com.asesoftware.bancow.negocio.NegocioRegistroArchivo;
 
@ManagedBean
@ViewScoped
public class RegistroArchivoFormManagedBean implements Serializable {

	
	@EJB
    private NegocioRegistroArchivo negocioRegistroArchivo;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegistroArchivoFormManagedBean.class.getName());
	
	private RegistroArchivo registroArchivo;
    
     
    @PostConstruct
    public void init() {   	
    	this.registroArchivo = new RegistroArchivo();
    }     
    /**
     * 
     */
    public void createRegistroArchivo() {
    	try {
    		negocioRegistroArchivo.crear(registroArchivo);
			FacesContext.getCurrentInstance().getExternalContext().redirect("registroArchivo.xhtml");
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
	public RegistroArchivo getRegistroArchivo() {
		return registroArchivo;
	}
	/**
	 * 
	 * @param registroArchivo
	 */
	public void setRegistroArchivo(RegistroArchivo registroArchivo) {
		this.registroArchivo = registroArchivo;
	}
   
}