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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import uk.co.jemos.podam.annotations.PodamExclude;
import java.math.BigDecimal;
import javax.persistence.OneToOne;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CORREO")
@NamedQuery(name = "Correo.findAll", query = "SELECT p FROM Correo p")
public class Correo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CORREO_PK = "mail";
	public static final String ENTIDAD_CORREO_ESTADO = "estado";
	public static final String ENTIDAD_CORREO_FUNCIONARIO_NUMERO_ID = "funcionarioNumeroId";
    private static final String[] ATRIBUTOS_ENTIDAD_CORREO
            = {ENTIDAD_CORREO_PK, ENTIDAD_CORREO_ESTADO, ENTIDAD_CORREO_FUNCIONARIO_NUMERO_ID};

	@Id
    @Column(name="MAIL")
	private String mail;

	@Column(name="ESTADO")
	private String estado;
	
    @PodamExclude
	@Column(name="FUNCIONARIO_NUMERO_ID")
	private BigDecimal funcionarioNumeroId;
	

	@OneToOne
	@JoinColumn(name="FUNCIONARIO_NUMERO_ID", referencedColumnName="NUMERO_ID", insertable = false, updatable = false)
	@PodamExclude
    private Funcionario funcionariocorreoFuncionarioFk;
    
		
	@OneToMany(mappedBy="correocorreoEnvCorreoFk")
	@PodamExclude
    private List<CorreoEnviado> correoEnvCorreoFkes;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public Correo(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public String getMail(){
		return this.mail;
	}
	
	public void setMail(String mail){
	
		this.mail = mail;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
	
		this.estado = estado;
	}
		
	public BigDecimal getFuncionarioNumeroId(){
		return this.funcionarioNumeroId;
	}
	
	public void setFuncionarioNumeroId(BigDecimal funcionarioNumeroId){
	
		this.funcionarioNumeroId = funcionarioNumeroId;
	}
		

    public List<CorreoEnviado> getCorreoEnvCorreoFkesList(){
		return this.correoEnvCorreoFkes;
	}
	
	public void setCorreoEnvCorreoFkesList(List<CorreoEnviado> correoEnvCorreoFkes){
		this.correoEnvCorreoFkes = correoEnvCorreoFkes;
	}
			
    public Funcionario getFuncionariocorreoFuncionarioFk(){
		return this.funcionariocorreoFuncionarioFk; 
	}
	
	public void setFuncionariocorreoFuncionarioFk(Funcionario funcionariocorreoFuncionarioFk){
		this.funcionariocorreoFuncionarioFk = funcionariocorreoFuncionarioFk;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CORREO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadCorreo() {
		return ATRIBUTOS_ENTIDAD_CORREO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.mail);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.funcionarioNumeroId);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Correo que se pasa
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
        final Correo other = (Correo) obj;
        
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        return Objects.equals(this.funcionarioNumeroId, other.funcionarioNumeroId);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

