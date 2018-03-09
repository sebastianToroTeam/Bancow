package com.asesoftware.bancow.web.bean;

import com.asesoftware.bancow.modelo.entidades.Usuario;
import com.asesoftware.bancow.negocio.NegocioUsuario;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author descobar
 */
@ManagedBean
@ViewScoped
public class LoginBean {

    @EJB
    private NegocioUsuario negocioUsuario;

    private Usuario usuario;

    private String usrNombre;
    private String usrPwd;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsrNombre() {
        return usrNombre;
    }

    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String buttonAction() {
        String usr = usrNombre.trim();
        String pass = usrPwd.trim();
        if (!usr.isEmpty() && !pass.isEmpty()) {
            usuario = negocioUsuario.validarUsuario(usr, pass);
            if (usuario != null) {

                try {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                    session.setAttribute("usuario", usuario);
                    HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
                    facesContext.getExternalContext().redirect(request.getContextPath());
                } catch (IOException ex) {
                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                    addErrorMessage("¡Error Fatal!");
                }
            }
            else{
                addErrorMessage("¡Usuario o Contraseña invalidos!");
            }
        }else
        {
            addWarningMessage("¡Usuario o Contraseña vacios!");
        }
        return null;
    }
    
    public void addErrorMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addWarningMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addInfoMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
