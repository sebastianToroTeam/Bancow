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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import uk.co.jemos.podam.annotations.PodamExclude;
import java.math.BigDecimal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="REGISTRO_ARCHIVO")
@NamedQuery(name = "RegistroArchivo.findAll", query = "SELECT p FROM RegistroArchivo p")
public class RegistroArchivo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_REGISTRO_ARCHIVO_PK_NUMERO = "registroArchivoPK.numero";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_PK_ARC_PROC_CODIGO_PROCESO = "registroArchivoPK.arcProcCodigoProceso";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_REGISTRO = "registro";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_ESTADO = "estado";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_CUENTA_ORIGEN = "cuentaOrigen";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_ID_TITULAR_ORIGEN = "idTitularOrigen";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_CUENTA_DESTINO = "cuentaDestino";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_ID_TITULAR_DESTINO = "idTitularDestino";
	public static final String ENTIDAD_REGISTRO_ARCHIVO_VALOR_TRANSFERENCIA = "valorTransferencia";
    private static final String[] ATRIBUTOS_ENTIDAD_REGISTRO_ARCHIVO
            = {ENTIDAD_REGISTRO_ARCHIVO_PK_ARC_PROC_CODIGO_PROCESO, ENTIDAD_REGISTRO_ARCHIVO_VALOR_TRANSFERENCIA, ENTIDAD_REGISTRO_ARCHIVO_ESTADO, ENTIDAD_REGISTRO_ARCHIVO_CUENTA_ORIGEN, ENTIDAD_REGISTRO_ARCHIVO_CUENTA_DESTINO, ENTIDAD_REGISTRO_ARCHIVO_ID_TITULAR_DESTINO, ENTIDAD_REGISTRO_ARCHIVO_ID_TITULAR_ORIGEN, ENTIDAD_REGISTRO_ARCHIVO_PK_NUMERO, ENTIDAD_REGISTRO_ARCHIVO_REGISTRO};

	@EmbeddedId
	private RegistroArchivoPK registroArchivoPK;

	@Column(name="REGISTRO")
	private String registro;
	
	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="CUENTA_ORIGEN")
	private String cuentaOrigen;
	
	@Column(name="ID_TITULAR_ORIGEN")
	private BigDecimal idTitularOrigen;
	
	@Column(name="CUENTA_DESTINO")
	private String cuentaDestino;
	
	@Column(name="ID_TITULAR_DESTINO")
	private BigDecimal idTitularDestino;
	
	@Column(name="VALOR_TRANSFERENCIA")
	private BigDecimal valorTransferencia;
	

	@ManyToOne
	@JoinColumn(name="ARC_PROC_CODIGO_PROCESO", referencedColumnName="CODIGO_PROCESO", insertable = false, updatable = false)
	@PodamExclude
    private ArchivoProcesado archivoProcesadoregArcProcesadoFk;
    
		
	@OneToMany(mappedBy="registroArchivotrasnfRegArcFk")
	@PodamExclude
    private List<Trasnferencia> trasnfRegArcFkes;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public RegistroArchivo(){
		registroArchivoPK = new RegistroArchivoPK();
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public RegistroArchivoPK getRegistroArchivoPK(){
		return this.registroArchivoPK;
	}
	
	public void setRegistroArchivoPK(RegistroArchivoPK registroArchivoPK){
		this.registroArchivoPK   = registroArchivoPK ;
	}  
	
	public String getRegistro(){
		return this.registro;
	}
	
	public void setRegistro(String registro){
	
		this.registro = registro;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
	
		this.estado = estado;
	}
		
	public String getCuentaOrigen(){
		return this.cuentaOrigen;
	}
	
	public void setCuentaOrigen(String cuentaOrigen){
	
		this.cuentaOrigen = cuentaOrigen;
	}
		
	public BigDecimal getIdTitularOrigen(){
		return this.idTitularOrigen;
	}
	
	public void setIdTitularOrigen(BigDecimal idTitularOrigen){
	
		this.idTitularOrigen = idTitularOrigen;
	}
		
	public String getCuentaDestino(){
		return this.cuentaDestino;
	}
	
	public void setCuentaDestino(String cuentaDestino){
	
		this.cuentaDestino = cuentaDestino;
	}
		
	public BigDecimal getIdTitularDestino(){
		return this.idTitularDestino;
	}
	
	public void setIdTitularDestino(BigDecimal idTitularDestino){
	
		this.idTitularDestino = idTitularDestino;
	}
		
	public BigDecimal getValorTransferencia(){
		return this.valorTransferencia;
	}
	
	public void setValorTransferencia(BigDecimal valorTransferencia){
	
		this.valorTransferencia = valorTransferencia;
	}
		

    public List<Trasnferencia> getTrasnfRegArcFkesList(){
		return this.trasnfRegArcFkes;
	}
	
	public void setTrasnfRegArcFkesList(List<Trasnferencia> trasnfRegArcFkes){
		this.trasnfRegArcFkes = trasnfRegArcFkes;
	}
			
    public ArchivoProcesado getArchivoProcesadoregArcProcesadoFk(){
		return this.archivoProcesadoregArcProcesadoFk; 
	}
	
	public void setArchivoProcesadoregArcProcesadoFk(ArchivoProcesado archivoProcesadoregArcProcesadoFk){
		this.archivoProcesadoregArcProcesadoFk = archivoProcesadoregArcProcesadoFk;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_REGISTRO_ARCHIVO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadRegistroArchivo() {
		return ATRIBUTOS_ENTIDAD_REGISTRO_ARCHIVO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.registroArchivoPK);        
        hash = 37 * hash + Objects.hashCode(this.registro);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.cuentaOrigen);
        hash = 37 * hash + Objects.hashCode(this.idTitularOrigen);
        hash = 37 * hash + Objects.hashCode(this.cuentaDestino);
        hash = 37 * hash + Objects.hashCode(this.idTitularDestino);
        hash = 37 * hash + Objects.hashCode(this.valorTransferencia);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RegistroArchivo que se pasa
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
        final RegistroArchivo other = (RegistroArchivo) obj;
        
        if (!Objects.equals(this.registroArchivoPK, other.registroArchivoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.registro, other.registro)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.cuentaOrigen, other.cuentaOrigen)) {
            return false;
        }
        
        if (!Objects.equals(this.idTitularOrigen, other.idTitularOrigen)) {
            return false;
        }
        
        if (!Objects.equals(this.cuentaDestino, other.cuentaDestino)) {
            return false;
        }
        
        if (!Objects.equals(this.idTitularDestino, other.idTitularDestino)) {
            return false;
        }
        
        return Objects.equals(this.valorTransferencia, other.valorTransferencia);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

