package com.asesoftware.bancow.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
	
import java.math.BigDecimal;

@Embeddable
public class CorreoEnviadoPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="ARC_PROC_CODIGO_PROCESO")
    
	private BigDecimal arcProcCodigoProceso;
  
	@Basic(optional = false)
    @NotNull
    @Column(name="CORREO_MAIL")
    
	private String correoMail;
  

	public CorreoEnviadoPK(){
		
	}

    public CorreoEnviadoPK(BigDecimal arcProcCodigoProceso, String correoMail) {
		this.arcProcCodigoProceso = arcProcCodigoProceso;       
		this.correoMail = correoMail;       
    }

    
	public BigDecimal getArcProcCodigoProceso(){
		return this.arcProcCodigoProceso;
	}
	
	public void setArcProcCodigoProceso(BigDecimal arcProcCodigoProceso){
	
		this.arcProcCodigoProceso = arcProcCodigoProceso;
	}
		
    
	public String getCorreoMail(){
		return this.correoMail;
	}
	
	public void setCorreoMail(String correoMail){
	
		this.correoMail = correoMail;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.arcProcCodigoProceso);
        hash = 37 * hash + Objects.hashCode(this.correoMail);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoEnviadoPK que se pasa
     * como parámetro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relación con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * @return Verdadero si esta instancia y la que se pasan como parámetros son
     * iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CorreoEnviadoPK other = (CorreoEnviadoPK) obj;
        
        
        if (!Objects.equals(this.arcProcCodigoProceso, other.arcProcCodigoProceso)) {
            return false;
        }
        
        return Objects.equals(this.correoMail, other.correoMail);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("arcProcCodigoProceso");
		 cadena.append(this.arcProcCodigoProceso);
	 	cadena.append(", ");
         
	     cadena.append("correoMail");
		 cadena.append(this.correoMail);
         
     	return cadena.toString(); 
     }

} 
