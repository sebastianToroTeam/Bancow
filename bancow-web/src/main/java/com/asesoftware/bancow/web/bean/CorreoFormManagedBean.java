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

import com.asesoftware.bancow.modelo.entidades.Correo;
import com.asesoftware.bancow.modelo.entidades.Funcionario;
import com.asesoftware.bancow.negocio.NegocioCorreo;
import java.util.ArrayList;
import java.util.List;
 
@ManagedBean
@ViewScoped
public class CorreoFormManagedBean implements Serializable {

	
	@EJB
    private NegocioCorreo negocioCorreo;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CorreoFormManagedBean.class.getName());
	
	private Correo correo;
    
     
    @PostConstruct
    public void init() {   	
    	this.correo = new Correo();
    }     
    /**
     * 
     */
    public void createCorreo() {
    	try {
    		negocioCorreo.crear(correo);
			FacesContext.getCurrentInstance().getExternalContext().redirect("correo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro no pudo ser creado", "Corrija los datos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
   //
    
    public List<Funcionario> getListaFuncionarios() {
            List<Funcionario> funcionarios = new ArrayList<>();
            funcionarios = negocioCorreo.listafuncionarios();           
           return funcionarios;
	}
    
    /**
     * 
     * @return
     */
	public Correo getCorreo() {
		return correo;
	}
	/**
	 * 
	 * @param correo
	 */
	public void setCorreo(Correo correo) {
		this.correo = correo;
	}
   
}