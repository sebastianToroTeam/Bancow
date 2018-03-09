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
import javax.persistence.JoinColumns;
import javax.persistence.Temporal;
import java.util.Date;
import uk.co.jemos.podam.annotations.PodamExclude;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="TRASNFERENCIA")
@NamedQuery(name = "Trasnferencia.findAll", query = "SELECT p FROM Trasnferencia p")
public class Trasnferencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TRASNFERENCIA_PK_CUENTA_ORGEN = "trasnferenciaPK.cuentaOrgen";
	public static final String ENTIDAD_TRASNFERENCIA_PK_CUENTA_DESTINO = "trasnferenciaPK.cuentaDestino";
	public static final String ENTIDAD_TRASNFERENCIA_PK_ARC_CODIGO_PROCESO = "trasnferenciaPK.arcCodigoProceso";
	public static final String ENTIDAD_TRASNFERENCIA_PK_REG_ARC_NUMERO = "trasnferenciaPK.regArcNumero";
	public static final String ENTIDAD_TRASNFERENCIA_VALOR = "valor";
	public static final String ENTIDAD_TRASNFERENCIA_FECHA = "fecha";
    private static final String[] ATRIBUTOS_ENTIDAD_TRASNFERENCIA
            = {ENTIDAD_TRASNFERENCIA_PK_CUENTA_ORGEN, ENTIDAD_TRASNFERENCIA_FECHA, ENTIDAD_TRASNFERENCIA_PK_CUENTA_DESTINO, ENTIDAD_TRASNFERENCIA_PK_REG_ARC_NUMERO, ENTIDAD_TRASNFERENCIA_VALOR, ENTIDAD_TRASNFERENCIA_PK_ARC_CODIGO_PROCESO};

	@EmbeddedId
	private TrasnferenciaPK trasnferenciaPK;

	@Column(name="VALOR")
	private BigDecimal valor;
	
	@Column(name="FECHA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fecha;
	

	@ManyToOne
	@JoinColumn(name="CUENTA_ORGEN", referencedColumnName="NUMERO", insertable = false, updatable = false)
	@PodamExclude
    private Cuenta cuentatrasnfCuentaOriFk;
    
		
	@ManyToOne
	@JoinColumn(name="CUENTA_DESTINO", referencedColumnName="NUMERO", insertable = false, updatable = false)
	@PodamExclude
    private Cuenta cuentatrasnfCuentaDesFk;
    
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "ARC_CODIGO_PROCESO", referencedColumnName="ARC_PROC_CODIGO_PROCESO", insertable = false, updatable = false),
	    @JoinColumn(name = "REG_ARC_NUMERO", referencedColumnName="NUMERO", insertable = false, updatable = false)	    
	})		
	@PodamExclude
    private RegistroArchivo registroArchivotrasnfRegArcFk;
    
		

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public Trasnferencia(){
		trasnferenciaPK = new TrasnferenciaPK();
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public TrasnferenciaPK getTrasnferenciaPK(){
		return this.trasnferenciaPK;
	}
	
	public void setTrasnferenciaPK(TrasnferenciaPK trasnferenciaPK){
		this.trasnferenciaPK   = trasnferenciaPK ;
	}  
	
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
	
		this.valor = valor;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
	
		this.fecha = fecha;
	}
		

    public Cuenta getCuentatrasnfCuentaOriFk(){
		return this.cuentatrasnfCuentaOriFk; 
	}
	
	public void setCuentatrasnfCuentaOriFk(Cuenta cuentatrasnfCuentaOriFk){
		this.cuentatrasnfCuentaOriFk = cuentatrasnfCuentaOriFk;
	}
    public Cuenta getCuentatrasnfCuentaDesFk(){
		return this.cuentatrasnfCuentaDesFk; 
	}
	
	public void setCuentatrasnfCuentaDesFk(Cuenta cuentatrasnfCuentaDesFk){
		this.cuentatrasnfCuentaDesFk = cuentatrasnfCuentaDesFk;
	}
    public RegistroArchivo getRegistroArchivotrasnfRegArcFk(){
		return this.registroArchivotrasnfRegArcFk; 
	}
	
	public void setRegistroArchivotrasnfRegArcFk(RegistroArchivo registroArchivotrasnfRegArcFk){
		this.registroArchivotrasnfRegArcFk = registroArchivotrasnfRegArcFk;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TRASNFERENCIA) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadTrasnferencia() {
		return ATRIBUTOS_ENTIDAD_TRASNFERENCIA;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.trasnferenciaPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Trasnferencia que se pasa
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
        final Trasnferencia other = (Trasnferencia) obj;
        
        if (!Objects.equals(this.trasnferenciaPK, other.trasnferenciaPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        return Objects.equals(this.fecha, other.fecha);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

