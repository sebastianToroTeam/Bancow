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
import uk.co.jemos.podam.annotations.PodamExclude;
import java.math.BigDecimal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="TITULAR")
@NamedQuery(name = "Titular.findAll", query = "SELECT p FROM Titular p")
public class Titular implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TITULAR_PK = "identificacion";
	public static final String ENTIDAD_TITULAR_NOMBRES = "nombres";
	public static final String ENTIDAD_TITULAR_PRIMER_APELLIDO = "primerApellido";
	public static final String ENTIDAD_TITULAR_SEGUNDO_APELLIDO = "segundoApellido";
    private static final String[] ATRIBUTOS_ENTIDAD_TITULAR
            = {ENTIDAD_TITULAR_PK, ENTIDAD_TITULAR_NOMBRES, ENTIDAD_TITULAR_SEGUNDO_APELLIDO, ENTIDAD_TITULAR_PRIMER_APELLIDO};

	@Id
    @Column(name="IDENTIFICACION")
	private BigDecimal identificacion;

	@Column(name="NOMBRES")
	private String nombres;
	
	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;
	
	@Column(name="SEGUNDO_APELLIDO")
	private String segundoApellido;
	

	@OneToMany(mappedBy="titularcuentaTitularFk")
	@PodamExclude
    private List<Cuenta> cuentaTitularFkes;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public Titular(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public BigDecimal getIdentificacion(){
		return this.identificacion;
	}
	
	public void setIdentificacion(BigDecimal identificacion){
	
		this.identificacion = identificacion;
	}
	
	public String getNombres(){
		return this.nombres;
	}
	
	public void setNombres(String nombres){
	
		this.nombres = nombres;
	}
		
	public String getPrimerApellido(){
		return this.primerApellido;
	}
	
	public void setPrimerApellido(String primerApellido){
	
		this.primerApellido = primerApellido;
	}
		
	public String getSegundoApellido(){
		return this.segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido){
	
		this.segundoApellido = segundoApellido;
	}
		

    public List<Cuenta> getCuentaTitularFkesList(){
		return this.cuentaTitularFkes;
	}
	
	public void setCuentaTitularFkesList(List<Cuenta> cuentaTitularFkes){
		this.cuentaTitularFkes = cuentaTitularFkes;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TITULAR) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadTitular() {
		return ATRIBUTOS_ENTIDAD_TITULAR;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.identificacion);        
        hash = 37 * hash + Objects.hashCode(this.nombres);
        hash = 37 * hash + Objects.hashCode(this.primerApellido);
        hash = 37 * hash + Objects.hashCode(this.segundoApellido);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Titular que se pasa
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
        final Titular other = (Titular) obj;
        
        if (!Objects.equals(this.identificacion, other.identificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.nombres, other.nombres)) {
            return false;
        }
        
        if (!Objects.equals(this.primerApellido, other.primerApellido)) {
            return false;
        }
        
        return Objects.equals(this.segundoApellido, other.segundoApellido);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

