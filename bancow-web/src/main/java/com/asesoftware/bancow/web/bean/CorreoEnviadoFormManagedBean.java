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

import com.asesoftware.bancow.modelo.entidades.CorreoEnviado;
import com.asesoftware.bancow.negocio.NegocioCorreoEnviado;
 
@ManagedBean
@ViewScoped
public class CorreoEnviadoFormManagedBean implements Serializable {

	
	@EJB
    private NegocioCorreoEnviado negocioCorreoEnviado;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CorreoEnviadoFormManagedBean.class.getName());
	
	private CorreoEnviado correoEnviado;
    
     
    @PostConstruct
    public void init() {   	
    	this.correoEnviado = new CorreoEnviado();
    }     
    /**
     * 
     */
    public void createCorreoEnviado() {
    	try {
    		negocioCorreoEnviado.crear(correoEnviado);
			FacesContext.getCurrentInstance().getExternalContext().redirect("correoEnviado.xhtml");
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
	public CorreoEnviado getCorreoEnviado() {
		return correoEnviado;
	}
	/**
	 * 
	 * @param correoEnviado
	 */
	public void setCorreoEnviado(CorreoEnviado correoEnviado) {
		this.correoEnviado = correoEnviado;
	}
   
}