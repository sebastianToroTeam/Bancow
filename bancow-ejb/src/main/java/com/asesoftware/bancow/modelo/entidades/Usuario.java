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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import uk.co.jemos.podam.annotations.PodamExclude;
import java.math.BigDecimal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="USUARIO")
@NamedQuery(name = "Usuario.findAll", query = "SELECT p FROM Usuario p")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_USUARIO_PK = "login";
	public static final String ENTIDAD_USUARIO_PWD = "pwd";
	public static final String ENTIDAD_USUARIO_ROL = "rol";
	public static final String ENTIDAD_USUARIO_ESTADO = "estado";
	public static final String ENTIDAD_USUARIO_FUNCIONARIO_NUMERO_ID = "funcionarioNumeroId";
    private static final String[] ATRIBUTOS_ENTIDAD_USUARIO
            = {ENTIDAD_USUARIO_ESTADO, ENTIDAD_USUARIO_PK, ENTIDAD_USUARIO_FUNCIONARIO_NUMERO_ID, ENTIDAD_USUARIO_PWD, ENTIDAD_USUARIO_ROL};

	@Id
    @Column(name="LOGIN")
	private String login;

	@Column(name="PWD")
	private String pwd;
	
	@Column(name="ROL")
	private String rol;
	
	@Column(name="ESTADO")
	private String estado;
	
    @PodamExclude
	@Column(name="FUNCIONARIO_NUMERO_ID")
	private BigDecimal funcionarioNumeroId;
	

	@ManyToOne
	@JoinColumn(name="FUNCIONARIO_NUMERO_ID", referencedColumnName="NUMERO_ID", insertable = false, updatable = false)
	@PodamExclude
    private Funcionario funcionariousuarioFuncionarioFk;
    
		

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public Usuario(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public String getLogin(){
		return this.login;
	}
	
	public void setLogin(String login){
	
		this.login = login;
	}
	
	public String getPwd(){
		return this.pwd;
	}
	
	public void setPwd(String pwd){
	
		this.pwd = pwd;
	}
		
	public String getRol(){
		return this.rol;
	}
	
	public void setRol(String rol){
	
		this.rol = rol;
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
		

    public Funcionario getFuncionariousuarioFuncionarioFk(){
		return this.funcionariousuarioFuncionarioFk; 
	}
	
	public void setFuncionariousuarioFuncionarioFk(Funcionario funcionariousuarioFuncionarioFk){
		this.funcionariousuarioFuncionarioFk = funcionariousuarioFuncionarioFk;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_USUARIO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadUsuario() {
		return ATRIBUTOS_ENTIDAD_USUARIO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.login);        
        hash = 37 * hash + Objects.hashCode(this.pwd);
        hash = 37 * hash + Objects.hashCode(this.rol);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.funcionarioNumeroId);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Usuario que se pasa
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
        final Usuario other = (Usuario) obj;
        
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        
        if (!Objects.equals(this.pwd, other.pwd)) {
            return false;
        }
        
        if (!Objects.equals(this.rol, other.rol)) {
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

