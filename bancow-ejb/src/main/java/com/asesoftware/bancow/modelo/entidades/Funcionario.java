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
import javax.persistence.OneToOne;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="FUNCIONARIO")
@NamedQuery(name = "Funcionario.findAll", query = "SELECT p FROM Funcionario p")
public class Funcionario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_FUNCIONARIO_PK = "numeroId";
	public static final String ENTIDAD_FUNCIONARIO_NOMBRES = "nombres";
	public static final String ENTIDAD_FUNCIONARIO_APELLIDOS = "apellidos";
    private static final String[] ATRIBUTOS_ENTIDAD_FUNCIONARIO
            = {ENTIDAD_FUNCIONARIO_APELLIDOS, ENTIDAD_FUNCIONARIO_PK, ENTIDAD_FUNCIONARIO_NOMBRES};

	@Id
    @Column(name="NUMERO_ID")
	private BigDecimal numeroId;

	@Column(name="NOMBRES")
	private String nombres;
	
	@Column(name="APELLIDOS")
	private String apellidos;
	

	@OneToOne(mappedBy="funcionariocorreoFuncionarioFk")
	@PodamExclude
    private Correo correoFuncionarioFkes;
	@OneToMany(mappedBy="funcionariousuarioFuncionarioFk")
	@PodamExclude
    private List<Usuario> usuarioFuncionarioFkes;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public Funcionario(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public BigDecimal getNumeroId(){
		return this.numeroId;
	}
	
	public void setNumeroId(BigDecimal numeroId){
	
		this.numeroId = numeroId;
	}
	
	public String getNombres(){
		return this.nombres;
	}
	
	public void setNombres(String nombres){
	
		this.nombres = nombres;
	}
		
	public String getApellidos(){
		return this.apellidos;
	}
	
	public void setApellidos(String apellidos){
	
		this.apellidos = apellidos;
	}
		

    public Correo getCorreoFuncionarioFkesList(){
		return this.correoFuncionarioFkes;
	}
	
	public void setCorreoFuncionarioFkesList(Correo correoFuncionarioFkes){
		this.correoFuncionarioFkes = correoFuncionarioFkes;
	}
			
    public List<Usuario> getUsuarioFuncionarioFkesList(){
		return this.usuarioFuncionarioFkes;
	}
	
	public void setUsuarioFuncionarioFkesList(List<Usuario> usuarioFuncionarioFkes){
		this.usuarioFuncionarioFkes = usuarioFuncionarioFkes;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_FUNCIONARIO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadFuncionario() {
		return ATRIBUTOS_ENTIDAD_FUNCIONARIO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.numeroId);        
        hash = 37 * hash + Objects.hashCode(this.nombres);
        hash = 37 * hash + Objects.hashCode(this.apellidos);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Funcionario que se pasa
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
        final Funcionario other = (Funcionario) obj;
        
        if (!Objects.equals(this.numeroId, other.numeroId)) {
            return false;
        }
        
        if (!Objects.equals(this.nombres, other.nombres)) {
            return false;
        }
        
        return Objects.equals(this.apellidos, other.apellidos);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

