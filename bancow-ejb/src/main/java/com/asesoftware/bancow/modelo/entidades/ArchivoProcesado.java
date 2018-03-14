package com.asesoftware.bancow.modelo.entidades;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.util.Date;
import uk.co.jemos.podam.annotations.PodamExclude;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="ARCHIVO_PROCESADO")
@NamedQuery(name = "ArchivoProcesado.findAll", query = "SELECT p FROM ArchivoProcesado p")
public class ArchivoProcesado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ARCHIVO_PROCESADO_PK = "codigoProceso";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_TIPO_PROCESO = "tipoProceso";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_ESTADO = "estado";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_FECHA_EJECUCION = "fechaEjecucion";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_CANTIDAD_REGISTROS = "cantidadRegistros";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_VALOR_TOTAL = "valorTotal";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_TRANSFERENCIAS_EXITOSAS = "transferenciasExitosas";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_TRANSFERENCIAS_FALLIDAS = "transferenciasFallidas";
	public static final String ENTIDAD_ARCHIVO_PROCESADO_CONVENIO_CODIGO = "convenioCodigo";
    private static final String[] ATRIBUTOS_ENTIDAD_ARCHIVO_PROCESADO
            = {ENTIDAD_ARCHIVO_PROCESADO_ESTADO, ENTIDAD_ARCHIVO_PROCESADO_TRANSFERENCIAS_FALLIDAS, ENTIDAD_ARCHIVO_PROCESADO_FECHA_EJECUCION, ENTIDAD_ARCHIVO_PROCESADO_TIPO_PROCESO, ENTIDAD_ARCHIVO_PROCESADO_VALOR_TOTAL, ENTIDAD_ARCHIVO_PROCESADO_CANTIDAD_REGISTROS, ENTIDAD_ARCHIVO_PROCESADO_TRANSFERENCIAS_EXITOSAS, ENTIDAD_ARCHIVO_PROCESADO_CONVENIO_CODIGO, ENTIDAD_ARCHIVO_PROCESADO_PK, ENTIDAD_ARCHIVO_PROCESADO_NOMBRE_ARCHIVO};

	@Id        
        @GeneratedValue(generator="InvSeq") 
        @SequenceGenerator(name="InvSeq",sequenceName="SEQC_ARCHIVO_PROCESADO", allocationSize=1)
        @Column(name="CODIGO_PROCESO")
	private BigDecimal codigoProceso;

	@Column(name="TIPO_PROCESO")
	private String tipoProceso;
	
	@Column(name="NOMBRE_ARCHIVO")
	private String nombreArchivo;
	
	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="FECHA_EJECUCION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaEjecucion;
	
	@Column(name="CANTIDAD_REGISTROS")
	private BigDecimal cantidadRegistros;
	
	@Column(name="VALOR_TOTAL")
	private BigDecimal valorTotal;
	
	@Column(name="TRANSFERENCIAS_EXITOSAS")
	private BigDecimal transferenciasExitosas;
	
	@Column(name="TRANSFERENCIAS_FALLIDAS")
	private BigDecimal transferenciasFallidas;
	
    @PodamExclude
	@Column(name="CONVENIO_CODIGO")
	private String convenioCodigo;
	

	@ManyToOne
	@JoinColumn(name="CONVENIO_CODIGO", referencedColumnName="CODIGO_CONVENIO", insertable = false, updatable = false)
	@PodamExclude
    private Convenio convenioarcProcConvenioFk;
    
		
	@OneToMany(mappedBy="archivoProcesadocorreoEnvArcProcFk")
	@PodamExclude
    private List<CorreoEnviado> correoEnvArcProcFkes;
	@OneToMany(mappedBy="archivoProcesadoregArcProcesadoFk")
	@PodamExclude
    private List<RegistroArchivo> regArcProcesadoFkes;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public ArchivoProcesado(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public BigDecimal getCodigoProceso(){
		return this.codigoProceso;
	}
	
	public void setCodigoProceso(BigDecimal codigoProceso){
	
		this.codigoProceso = codigoProceso;
	}
	
	public String getTipoProceso(){
		return this.tipoProceso;
	}
	
	public void setTipoProceso(String tipoProceso){
	
		this.tipoProceso = tipoProceso;
	}
		
	public String getNombreArchivo(){
		return this.nombreArchivo;
	}
	
	public void setNombreArchivo(String nombreArchivo){
	
		this.nombreArchivo = nombreArchivo;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
	
		this.estado = estado;
	}
		
	public Date getFechaEjecucion(){
		return this.fechaEjecucion;
	}
	
	public void setFechaEjecucion(Date fechaEjecucion){
	
		this.fechaEjecucion = fechaEjecucion;
	}
		
	public BigDecimal getCantidadRegistros(){
		return this.cantidadRegistros;
	}
	
	public void setCantidadRegistros(BigDecimal cantidadRegistros){
	
		this.cantidadRegistros = cantidadRegistros;
	}
		
	public BigDecimal getValorTotal(){
		return this.valorTotal;
	}
	
	public void setValorTotal(BigDecimal valorTotal){
	
		this.valorTotal = valorTotal;
	}
		
	public BigDecimal getTransferenciasExitosas(){
		return this.transferenciasExitosas;
	}
	
	public void setTransferenciasExitosas(BigDecimal transferenciasExitosas){
	
		this.transferenciasExitosas = transferenciasExitosas;
	}
		
	public BigDecimal getTransferenciasFallidas(){
		return this.transferenciasFallidas;
	}
	
	public void setTransferenciasFallidas(BigDecimal transferenciasFallidas){
	
		this.transferenciasFallidas = transferenciasFallidas;
	}
		
	public String getConvenioCodigo(){
		return this.convenioCodigo;
	}
	
	public void setConvenioCodigo(String convenioCodigo){
	
		this.convenioCodigo = convenioCodigo;
	}
		

    public List<CorreoEnviado> getCorreoEnvArcProcFkesList(){
		return this.correoEnvArcProcFkes;
	}
	
	public void setCorreoEnvArcProcFkesList(List<CorreoEnviado> correoEnvArcProcFkes){
		this.correoEnvArcProcFkes = correoEnvArcProcFkes;
	}
			
    public List<RegistroArchivo> getRegArcProcesadoFkesList(){
		return this.regArcProcesadoFkes;
	}
	
	public void setRegArcProcesadoFkesList(List<RegistroArchivo> regArcProcesadoFkes){
		this.regArcProcesadoFkes = regArcProcesadoFkes;
	}
			
    public Convenio getConvenioarcProcConvenioFk(){
		return this.convenioarcProcConvenioFk; 
	}
	
	public void setConvenioarcProcConvenioFk(Convenio convenioarcProcConvenioFk){
		this.convenioarcProcConvenioFk = convenioarcProcConvenioFk;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ARCHIVO_PROCESADO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadArchivoProcesado() {
		return ATRIBUTOS_ENTIDAD_ARCHIVO_PROCESADO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigoProceso);        
        hash = 37 * hash + Objects.hashCode(this.tipoProceso);
        hash = 37 * hash + Objects.hashCode(this.nombreArchivo);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaEjecucion);
        hash = 37 * hash + Objects.hashCode(this.cantidadRegistros);
        hash = 37 * hash + Objects.hashCode(this.valorTotal);
        hash = 37 * hash + Objects.hashCode(this.transferenciasExitosas);
        hash = 37 * hash + Objects.hashCode(this.transferenciasFallidas);
        hash = 37 * hash + Objects.hashCode(this.convenioCodigo);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ArchivoProcesado que se pasa
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
        final ArchivoProcesado other = (ArchivoProcesado) obj;
        
        if (!Objects.equals(this.codigoProceso, other.codigoProceso)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoProceso, other.tipoProceso)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEjecucion, other.fechaEjecucion)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadRegistros, other.cantidadRegistros)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTotal, other.valorTotal)) {
            return false;
        }
        
        if (!Objects.equals(this.transferenciasExitosas, other.transferenciasExitosas)) {
            return false;
        }
        
        if (!Objects.equals(this.transferenciasFallidas, other.transferenciasFallidas)) {
            return false;
        }
        
        return Objects.equals(this.convenioCodigo, other.convenioCodigo);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

