package com.asesoftware.bancow.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
	
import java.math.BigDecimal;

@Embeddable
public class RegistroArchivoPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="NUMERO")
    
	private BigDecimal numero;
  
	@Basic(optional = false)
    @NotNull
    @Column(name="ARC_PROC_CODIGO_PROCESO")
    
	private BigDecimal arcProcCodigoProceso;
  

	public RegistroArchivoPK(){
		
	}

    public RegistroArchivoPK(BigDecimal numero, BigDecimal arcProcCodigoProceso) {
		this.numero = numero;       
		this.arcProcCodigoProceso = arcProcCodigoProceso;       
    }

    
	public BigDecimal getNumero(){
		return this.numero;
	}
	
	public void setNumero(BigDecimal numero){
	
		this.numero = numero;
	}
		
    
	public BigDecimal getArcProcCodigoProceso(){
		return this.arcProcCodigoProceso;
	}
	
	public void setArcProcCodigoProceso(BigDecimal arcProcCodigoProceso){
	
		this.arcProcCodigoProceso = arcProcCodigoProceso;
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
        hash = 37 * hash + Objects.hashCode(this.arcProcCodigoProceso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RegistroArchivoPK que se pasa
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
        final RegistroArchivoPK other = (RegistroArchivoPK) obj;
        
        
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        
        return Objects.equals(this.arcProcCodigoProceso, other.arcProcCodigoProceso);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("numero");
		 cadena.append(this.numero);
	 	cadena.append(", ");
         
	     cadena.append("arcProcCodigoProceso");
		 cadena.append(this.arcProcCodigoProceso);
         
     	return cadena.toString(); 
     }

} 
