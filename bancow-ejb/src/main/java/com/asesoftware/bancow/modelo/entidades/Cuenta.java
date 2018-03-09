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
import uk.co.jemos.podam.annotations.PodamExclude;
import java.math.BigDecimal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CUENTA")
@NamedQuery(name = "Cuenta.findAll", query = "SELECT p FROM Cuenta p")
public class Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CUENTA_PK = "numero";
	public static final String ENTIDAD_CUENTA_ESTADO = "estado";
	public static final String ENTIDAD_CUENTA_SALDO_DISPONIBLE = "saldoDisponible";
	public static final String ENTIDAD_CUENTA_GMF = "gmf";
	public static final String ENTIDAD_CUENTA_CONDICION = "condicion";
	public static final String ENTIDAD_CUENTA_TITULAR_NUMERO_ID = "titularNumeroId";
    private static final String[] ATRIBUTOS_ENTIDAD_CUENTA
            = {ENTIDAD_CUENTA_GMF, ENTIDAD_CUENTA_TITULAR_NUMERO_ID, ENTIDAD_CUENTA_SALDO_DISPONIBLE, ENTIDAD_CUENTA_PK, ENTIDAD_CUENTA_CONDICION, ENTIDAD_CUENTA_ESTADO};

	@Id
    @Column(name="NUMERO")
	private String numero;

	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="SALDO_DISPONIBLE")
	private BigDecimal saldoDisponible;
	
	@Column(name="GMF")
	private String gmf;
	
	@Column(name="CONDICION")
	private String condicion;
	
    @PodamExclude
	@Column(name="TITULAR_NUMERO_ID")
	private BigDecimal titularNumeroId;
	

	@ManyToOne
	@JoinColumn(name="TITULAR_NUMERO_ID", referencedColumnName="IDENTIFICACION", insertable = false, updatable = false)
	@PodamExclude
    private Titular titularcuentaTitularFk;
    
		
	@OneToMany(mappedBy="cuentatrasnfCuentaOriFk")
	@PodamExclude
    private List<Trasnferencia> trasnfCuentaOriFkes;
	@OneToMany(mappedBy="cuentatrasnfCuentaDesFk")
	@PodamExclude
    private List<Trasnferencia> trasnfCuentaDesFkes;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public Cuenta(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public String getNumero(){
		return this.numero;
	}
	
	public void setNumero(String numero){
	
		this.numero = numero;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
	
		this.estado = estado;
	}
		
	public BigDecimal getSaldoDisponible(){
		return this.saldoDisponible;
	}
	
	public void setSaldoDisponible(BigDecimal saldoDisponible){
	
		this.saldoDisponible = saldoDisponible;
	}
		
	public String getGmf(){
		return this.gmf;
	}
	
	public void setGmf(String gmf){
	
		this.gmf = gmf;
	}
		
	public String getCondicion(){
		return this.condicion;
	}
	
	public void setCondicion(String condicion){
	
		this.condicion = condicion;
	}
		
	public BigDecimal getTitularNumeroId(){
		return this.titularNumeroId;
	}
	
	public void setTitularNumeroId(BigDecimal titularNumeroId){
	
		this.titularNumeroId = titularNumeroId;
	}
		

    public List<Trasnferencia> getTrasnfCuentaOriFkesList(){
		return this.trasnfCuentaOriFkes;
	}
	
	public void setTrasnfCuentaOriFkesList(List<Trasnferencia> trasnfCuentaOriFkes){
		this.trasnfCuentaOriFkes = trasnfCuentaOriFkes;
	}
			
    public List<Trasnferencia> getTrasnfCuentaDesFkesList(){
		return this.trasnfCuentaDesFkes;
	}
	
	public void setTrasnfCuentaDesFkesList(List<Trasnferencia> trasnfCuentaDesFkes){
		this.trasnfCuentaDesFkes = trasnfCuentaDesFkes;
	}
			
    public Titular getTitularcuentaTitularFk(){
		return this.titularcuentaTitularFk; 
	}
	
	public void setTitularcuentaTitularFk(Titular titularcuentaTitularFk){
		this.titularcuentaTitularFk = titularcuentaTitularFk;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CUENTA) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadCuenta() {
		return ATRIBUTOS_ENTIDAD_CUENTA;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.numero);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.saldoDisponible);
        hash = 37 * hash + Objects.hashCode(this.gmf);
        hash = 37 * hash + Objects.hashCode(this.condicion);
        hash = 37 * hash + Objects.hashCode(this.titularNumeroId);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Cuenta que se pasa
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
        final Cuenta other = (Cuenta) obj;
        
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.saldoDisponible, other.saldoDisponible)) {
            return false;
        }
        
        if (!Objects.equals(this.gmf, other.gmf)) {
            return false;
        }
        
        if (!Objects.equals(this.condicion, other.condicion)) {
            return false;
        }
        
        return Objects.equals(this.titularNumeroId, other.titularNumeroId);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

