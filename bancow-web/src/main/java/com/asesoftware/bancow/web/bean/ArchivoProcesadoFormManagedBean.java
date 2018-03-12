package com.asesoftware.bancow.web.bean;

import Utilidades.Constantes;
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
import com.asesoftware.bancow.modelo.entidades.ErrorValidacion;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
import com.asesoftware.bancow.negocio.NegocioArchivoProcesado;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class ArchivoProcesadoFormManagedBean implements Serializable {

    @EJB
    private NegocioArchivoProcesado negocioArchivoProcesado;
    UploadedFile file;
    List<String> respuesta;
    private boolean errorDato;
    List<String> listaRespuesta;
    List<RegistroArchivo> ListaDatos;

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
            String nombreArchivo = file.getFileName();
            if (negocioArchivoProcesado.ValidarNombreArchivo(nombreArchivo)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, nombreArchivo, " Ya fue cargado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().redirect("archivoProcesado.xhtml");
            }
            //validar si convenio existe
            //validarconvenio
            String[] convenio = nombreArchivo.split("-");
            String nombreConvenio = convenio.length > 1 ? convenio[0] : null;
            if (nombreConvenio != null) {
                System.err.println(nombreConvenio);
                List<Convenio> listaConvenios = getListaConvenios();
                if (!listaConvenios.isEmpty()) {
                    boolean existeConvenio = false;
                    for (Convenio items : listaConvenios) {
                        if (items.getNombre().equals(nombreConvenio)) {
                            existeConvenio = true;
                            break;
                        }
                    }
                    if (!existeConvenio) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Nombre de Convenio no valido, se ingreso: ", nombreConvenio);
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("archivoProcesado.xhtml");
                    }
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Nombre de archivo no valido", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("archivoProcesado.xhtml");
            }
            ///fin convenio
            //inicio archivo
            validarContenido();
            //Registrar el archivo
            negocioArchivoProcesado.crear(archivoProcesado);
            BigDecimal codigo=archivoProcesado.getCodigoProceso();
            
            //registar errores
            if(!respuesta.isEmpty()){
                ErrorValidacion error = new ErrorValidacion();
                for (String items : respuesta) {
                    error.setCodigoProceso(codigo);
                    error.setDescripcion(items);
                    //error.setNumeroRegistro(1);
                    negocioArchivoProcesado.registrarErrorValidacion(error);
                }
            }
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
    /* public boolean ValidarNombreArchivo(String nombreArchivo) {
        return negocioArchivoProcesado.listaConvenios();
    }*/
    //*************************************************
    ////validar contenido archivo
    //paso 2
    public void validarContenido() {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.file.getInputstream()));
            String line = br.readLine();
            int cont = 1;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String errorGeneral = "";
            if (null != line) {
                while (null != line) {
                    String[] fields = line.split(";");
                    String Errorlabel;
                    //validar estructura
                    int numeroFilas = fields != null ? fields.length : 0;

                    if (numeroFilas != 3) {
                        Errorlabel = String.format(Constantes.ERROR_NEG_1, Constantes.encabezadoField, cont, Constantes.TipoErroFormatoField, "no tiene el número de campos especificado (3)");
                        listaRespuesta.add(Integer.toString(cont));
                        listaRespuesta.add(Errorlabel);
                        respuesta.add(Errorlabel);
                    } else {
                        String fecha = format.format(this.archivoProcesado.getFechaEjecucion());
                        if (!fecha.equals(fields[0])) {
                            Errorlabel = String.format(Constantes.ERROR_NEG_1, Constantes.encabezadoField, cont, Constantes.TipoErroValortoField, " Error en la fecha seleccionada ");
                            listaRespuesta.add(Integer.toString(cont));
                            listaRespuesta.add(Errorlabel);
                            respuesta.add(Errorlabel);
                        }
                        //registrar los valores del encabezado
                        validarCampos(fields, cont, true);
                        if (!errorDato) {
                            this.archivoProcesado.setCantidadRegistros(new BigDecimal(fields[1]));
                            this.archivoProcesado.setValorTotal(new BigDecimal(fields[2]));
                        }else{
                              this.archivoProcesado.setCantidadRegistros(BigDecimal.ZERO);
                              this.archivoProcesado.setValorTotal(BigDecimal.ZERO);
                        }
                    }
                    line = null;
                    cont += 1;
                }// fin while
            } else {
                errorGeneral = String.format(Constantes.ERROR_NEG_1, Constantes.encabezadoField, cont, Constantes.TipoErroValortoField, " Archivo vacio ");
                listaRespuesta.add("1");
                listaRespuesta.add(errorGeneral);
                respuesta.add(errorGeneral);
            }
            //cerar archivo
            if (null != br) {
                br.close();
            }
        } catch (Exception ex) {
            System.err.println("ArchivoProcesadoManagedBean.validarContenido()" + ex.toString());
        }
    }

    //validar detalle
    private void validarCampos(String[] campos, int fila, boolean encabezado) {
        String errorGeneral;
        for (String item : campos) {
            //saber el estado validacion
            if (item.isEmpty()) {
                errorGeneral = String.format(Constantes.ERROR_NEG_2, encabezado ? Constantes.encabezadoField : Constantes.detalleField, fila, Constantes.TipoErroValortoField, " Dato vacio ");
                this.errorDato = true;
                listaRespuesta.add(Integer.toString(fila));
                listaRespuesta.add(errorGeneral);
                respuesta.add(errorGeneral);
            } else {
                if (!isNumeric(item)) {
                    errorGeneral = String.format(Constantes.ERROR_NEG_2, encabezado ? Constantes.encabezadoField : Constantes.detalleField, fila, Constantes.TipoErroValortoField, item);
                    this.errorDato = true;
                    listaRespuesta.add(Integer.toString(fila));
                    listaRespuesta.add(errorGeneral);
                    respuesta.add(errorGeneral);
                }
            }
        }//fin while

    }

    //validar si ujn campo es numero
    private static boolean isNumeric(String cadena) {
        try {
            if (cadena.contains(".")) {
                Double.parseDouble(cadena);
            } else {
                Integer.parseInt(cadena);
            }
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    //********************************************************
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

    public List<String> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<String> respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isErrorDato() {
        return errorDato;
    }

    public void setErrorDato(boolean errorDato) {
        this.errorDato = errorDato;
    }

}
