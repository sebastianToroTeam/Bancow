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

import com.asesoftware.bancow.modelo.entidades.Cuenta;
import com.asesoftware.bancow.negocio.NegocioCuenta;
 
@ManagedBean
@ViewScoped
public class CuentaFormManagedBean implements Serializable {

	
	@EJB
    private NegocioCuenta negocioCuenta;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CuentaFormManagedBean.class.getName());
	
	private Cuenta cuenta;
    
     
    @PostConstruct
    public void init() {   	
    	this.cuenta = new Cuenta();
    }     
    /**
     * 
     */
    public void createCuenta() {
    	try {
    		negocioCuenta.crear(cuenta);
			FacesContext.getCurrentInstance().getExternalContext().redirect("cuenta.xhtml");
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
	public Cuenta getCuenta() {
		return cuenta;
	}
	/**
	 * 
	 * @param cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
   
}