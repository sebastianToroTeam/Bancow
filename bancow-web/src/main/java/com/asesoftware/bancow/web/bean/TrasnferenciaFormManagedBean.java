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

import com.asesoftware.bancow.modelo.entidades.Trasnferencia;
import com.asesoftware.bancow.negocio.NegocioTrasnferencia;
 
@ManagedBean
@ViewScoped
public class TrasnferenciaFormManagedBean implements Serializable {

	
	@EJB
    private NegocioTrasnferencia negocioTrasnferencia;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TrasnferenciaFormManagedBean.class.getName());
	
	private Trasnferencia trasnferencia;
    
     
    @PostConstruct
    public void init() {   	
    	this.trasnferencia = new Trasnferencia();
    }     
    /**
     * 
     */
    public void createTrasnferencia() {
    	try {
    		negocioTrasnferencia.crear(trasnferencia);
			FacesContext.getCurrentInstance().getExternalContext().redirect("trasnferencia.xhtml");
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
	public Trasnferencia getTrasnferencia() {
		return trasnferencia;
	}
	/**
	 * 
	 * @param trasnferencia
	 */
	public void setTrasnferencia(Trasnferencia trasnferencia) {
		this.trasnferencia = trasnferencia;
	}
   
}