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

import com.asesoftware.bancow.modelo.entidades.Usuario;
import com.asesoftware.bancow.negocio.NegocioUsuario;
 
@ManagedBean
@ViewScoped
public class UsuarioFormManagedBean implements Serializable {

	
	@EJB
    private NegocioUsuario negocioUsuario;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UsuarioFormManagedBean.class.getName());
	
	private Usuario usuario;
    
     
    @PostConstruct
    public void init() {   	
    	this.usuario = new Usuario();
    }     
    /**
     * 
     */
    public void createUsuario() {
    	try {
    		negocioUsuario.crear(usuario);
			FacesContext.getCurrentInstance().getExternalContext().redirect("usuario.xhtml");
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
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
   
}