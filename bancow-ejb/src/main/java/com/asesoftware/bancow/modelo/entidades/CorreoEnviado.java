package com.asesoftware.bancow.modelo.entidades;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.util.Date;
import uk.co.jemos.podam.annotations.PodamExclude;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CORREO_ENVIADO")
@NamedQuery(name = "CorreoEnviado.findAll", query = "SELECT p FROM CorreoEnviado p")
public class CorreoEnviado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CORREO_ENVIADO_PK_ARC_PROC_CODIGO_PROCESO = "correoEnviadoPK.arcProcCodigoProceso";
	public static final String ENTIDAD_CORREO_ENVIADO_PK_CORREO_MAIL = "correoEnviadoPK.correoMail";
	public static final String ENTIDAD_CORREO_ENVIADO_FECHA = "fecha";
    private static final String[] ATRIBUTOS_ENTIDAD_CORREO_ENVIADO
            = {ENTIDAD_CORREO_ENVIADO_FECHA, ENTIDAD_CORREO_ENVIADO_PK_ARC_PROC_CODIGO_PROCESO, ENTIDAD_CORREO_ENVIADO_PK_CORREO_MAIL};

	@EmbeddedId
	private CorreoEnviadoPK correoEnviadoPK;

	@Column(name="FECHA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fecha;
	

	@ManyToOne
	@JoinColumn(name="ARC_PROC_CODIGO_PROCESO", referencedColumnName="CODIGO_PROCESO", insertable = false, updatable = false)
	@PodamExclude
    private ArchivoProcesado archivoProcesadocorreoEnvArcProcFk;
    
		
	@ManyToOne
	@JoinColumn(name="CORREO_MAIL", referencedColumnName="MAIL", insertable = false, updatable = false)
	@PodamExclude
    private Correo correocorreoEnvCorreoFk;
    
		

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public CorreoEnviado(){
		correoEnviadoPK = new CorreoEnviadoPK();
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public CorreoEnviadoPK getCorreoEnviadoPK(){
		return this.correoEnviadoPK;
	}
	
	public void setCorreoEnviadoPK(CorreoEnviadoPK correoEnviadoPK){
		this.correoEnviadoPK   = correoEnviadoPK ;
	}  
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
	
		this.fecha = fecha;
	}
		

    public ArchivoProcesado getArchivoProcesadocorreoEnvArcProcFk(){
		return this.archivoProcesadocorreoEnvArcProcFk; 
	}
	
	public void setArchivoProcesadocorreoEnvArcProcFk(ArchivoProcesado archivoProcesadocorreoEnvArcProcFk){
		this.archivoProcesadocorreoEnvArcProcFk = archivoProcesadocorreoEnvArcProcFk;
	}
    public Correo getCorreocorreoEnvCorreoFk(){
		return this.correocorreoEnvCorreoFk; 
	}
	
	public void setCorreocorreoEnvCorreoFk(Correo correocorreoEnvCorreoFk){
		this.correocorreoEnvCorreoFk = correocorreoEnvCorreoFk;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CORREO_ENVIADO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadCorreoEnviado() {
		return ATRIBUTOS_ENTIDAD_CORREO_ENVIADO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.correoEnviadoPK);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoEnviado que se pasa
     * como parámetro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relación con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parámetros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CorreoEnviado other = (CorreoEnviado) obj;
        
        if (!Objects.equals(this.correoEnviadoPK, other.correoEnviadoPK)) {
            return false;
        }
        
        return true;
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

