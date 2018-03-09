package com.asesoftware.bancow.modelo.entidades;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;
import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CONVENIO")
@NamedQuery(name = "Convenio.findAll", query = "SELECT p FROM Convenio p")
public class Convenio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CONVENIO_PK = "codigoConvenio";
	public static final String ENTIDAD_CONVENIO_NOMBRE = "nombre";
	public static final String ENTIDAD_CONVENIO_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_CONVENIO_ESTADO = "estado";
	public static final String ENTIDAD_CONVENIO_RUTA_CARGA_ARCHIVOS = "rutaCargaArchivos";
    private static final String[] ATRIBUTOS_ENTIDAD_CONVENIO
            = {ENTIDAD_CONVENIO_DESCRIPCION, ENTIDAD_CONVENIO_ESTADO, ENTIDAD_CONVENIO_NOMBRE, ENTIDAD_CONVENIO_RUTA_CARGA_ARCHIVOS, ENTIDAD_CONVENIO_PK};

	@Id
    @Column(name="CODIGO_CONVENIO")
	private String codigoConvenio;

	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="RUTA_CARGA_ARCHIVOS")
	private String rutaCargaArchivos;
	

	@OneToMany(mappedBy="convenioarcProcConvenioFk")
	@PodamExclude
    private List<ArchivoProcesado> arcProcConvenioFkes;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public Convenio(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public String getCodigoConvenio(){
		return this.codigoConvenio;
	}
	
	public void setCodigoConvenio(String codigoConvenio){
	
		this.codigoConvenio = codigoConvenio;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
	
		this.nombre = nombre;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
	
		this.descripcion = descripcion;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
	
		this.estado = estado;
	}
		
	public String getRutaCargaArchivos(){
		return this.rutaCargaArchivos;
	}
	
	public void setRutaCargaArchivos(String rutaCargaArchivos){
	
		this.rutaCargaArchivos = rutaCargaArchivos;
	}
		

    public List<ArchivoProcesado> getArcProcConvenioFkesList(){
		return this.arcProcConvenioFkes;
	}
	
	public void setArcProcConvenioFkesList(List<ArchivoProcesado> arcProcConvenioFkes){
		this.arcProcConvenioFkes = arcProcConvenioFkes;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CONVENIO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadConvenio() {
		return ATRIBUTOS_ENTIDAD_CONVENIO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigoConvenio);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.rutaCargaArchivos);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Convenio que se pasa
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
        final Convenio other = (Convenio) obj;
        
        if (!Objects.equals(this.codigoConvenio, other.codigoConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        return Objects.equals(this.rutaCargaArchivos, other.rutaCargaArchivos);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

