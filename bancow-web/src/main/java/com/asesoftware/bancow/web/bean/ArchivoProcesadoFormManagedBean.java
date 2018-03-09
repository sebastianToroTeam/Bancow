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

import com.asesoftware.bancow.modelo.entidades.ArchivoProcesado;
import com.asesoftware.bancow.modelo.entidades.Convenio;
import com.asesoftware.bancow.negocio.NegocioArchivoProcesado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class ArchivoProcesadoFormManagedBean implements Serializable {

    @EJB
    private NegocioArchivoProcesado negocioArchivoProcesado;
    UploadedFile file;


    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ArchivoProcesadoFormManagedBean.class.getName());

    private ArchivoProcesado archivoProcesado;

    @PostConstruct
    public void init() {
        this.archivoProcesado = new ArchivoProcesado();
    }

    /**
     *
     */
    public void createArchivoProcesado() {
        try {
            //validar archivo
              //this.file = this.file.getInputstream();
              Date fecha = this.archivoProcesado.getFechaEjecucion();
            //validar si archivo ya fue cargado anteriormente
            String nombre = file.getFileName();
            if(negocioArchivoProcesado.ValidarNombreArchivo(nombre))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, nombre ," Ya fue cargado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            //validar si convenio existe
            
            //Registrar el archivo
            negocioArchivoProcesado.crear(archivoProcesado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("archivoProcesado.xhtml");
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro no pudo ser creado", "Corrija los datos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    /*
    
    /*
    */
    // traer los Convenios activos
    public List<Convenio> getListaConvenios() {
        List<Convenio> convenio = new ArrayList<>();
        convenio = negocioArchivoProcesado.listaConvenios();
        return convenio;
    }
/*
    */
    // traer los Convenios activos
    public List<Convenio> ValidarNombreArchivo(String nombreArchivo) {        
        return negocioArchivoProcesado.listaConvenios();
    }
    /**
     *
     * @return
     */
    public ArchivoProcesado getArchivoProcesado() {
        return archivoProcesado;
    }

    /**
     *
     * @param archivoProcesado
     */
    public void setArchivoProcesado(ArchivoProcesado archivoProcesado) {
        this.archivoProcesado = archivoProcesado;
    }
    
        public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
