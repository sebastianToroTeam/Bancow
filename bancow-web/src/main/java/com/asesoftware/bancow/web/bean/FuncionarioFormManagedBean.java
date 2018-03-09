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

import com.asesoftware.bancow.modelo.entidades.Funcionario;
import com.asesoftware.bancow.negocio.NegocioFuncionario;
 
@ManagedBean
@ViewScoped
public class FuncionarioFormManagedBean implements Serializable {

	
	@EJB
    private NegocioFuncionario negocioFuncionario;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FuncionarioFormManagedBean.class.getName());
	
	private Funcionario funcionario;
    
     
    @PostConstruct
    public void init() {   	
    	this.funcionario = new Funcionario();
    }     
    /**
     * 
     */
    public void createFuncionario() {
    	try {
    		negocioFuncionario.crear(funcionario);
			FacesContext.getCurrentInstance().getExternalContext().redirect("funcionario.xhtml");
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
	public Funcionario getFuncionario() {
		return funcionario;
	}
	/**
	 * 
	 * @param funcionario
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
   
}