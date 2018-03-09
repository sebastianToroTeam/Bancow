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

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="ENC_DOMINIO")
@NamedQuery(name = "EncDominio.findAll", query = "SELECT p FROM EncDominio p")
public class EncDominio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ENC_DOMINIO_PK = "codigo";
	public static final String ENTIDAD_ENC_DOMINIO_NOMBRE = "nombre";
    private static final String[] ATRIBUTOS_ENTIDAD_ENC_DOMINIO
            = {ENTIDAD_ENC_DOMINIO_PK, ENTIDAD_ENC_DOMINIO_NOMBRE};

	@Id
    @Column(name="CODIGO")
	private String codigo;

	@Column(name="NOMBRE")
	private String nombre;
	


	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public EncDominio(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
	
		this.codigo = codigo;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
	
		this.nombre = nombre;
	}
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ENC_DOMINIO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadEncDominio() {
		return ATRIBUTOS_ENTIDAD_ENC_DOMINIO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigo);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EncDominio que se pasa
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
        final EncDominio other = (EncDominio) obj;
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        return true;
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

