package com.asesoftware.bancow.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
	
import java.math.BigDecimal;

@Embeddable
public class TrasnferenciaPK implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Basic(optional = false)
    @NotNull
    @Column(name="CUENTA_ORGEN")
    
	private String cuentaOrgen;
  
	@Basic(optional = false)
    @NotNull
    @Column(name="CUENTA_DESTINO")
    
	private String cuentaDestino;
  
	@Basic(optional = false)
    @NotNull
    @Column(name="ARC_CODIGO_PROCESO")
    
	private BigDecimal arcCodigoProceso;
  
	@Basic(optional = false)
    @NotNull
    @Column(name="REG_ARC_NUMERO")
    
	private BigDecimal regArcNumero;
  

	public TrasnferenciaPK(){
		
	}

    public TrasnferenciaPK(String cuentaOrgen, String cuentaDestino, BigDecimal arcCodigoProceso, BigDecimal regArcNumero) {
		this.cuentaOrgen = cuentaOrgen;       
		this.cuentaDestino = cuentaDestino;       
		this.arcCodigoProceso = arcCodigoProceso;       
		this.regArcNumero = regArcNumero;       
    }

    
	public String getCuentaOrgen(){
		return this.cuentaOrgen;
	}
	
	public void setCuentaOrgen(String cuentaOrgen){
	
		this.cuentaOrgen = cuentaOrgen;
	}
		
    
	public String getCuentaDestino(){
		return this.cuentaDestino;
	}
	
	public void setCuentaDestino(String cuentaDestino){
	
		this.cuentaDestino = cuentaDestino;
	}
		
    
	public BigDecimal getArcCodigoProceso(){
		return this.arcCodigoProceso;
	}
	
	public void setArcCodigoProceso(BigDecimal arcCodigoProceso){
	
		this.arcCodigoProceso = arcCodigoProceso;
	}
		
    
	public BigDecimal getRegArcNumero(){
		return this.regArcNumero;
	}
	
	public void setRegArcNumero(BigDecimal regArcNumero){
	
		this.regArcNumero = regArcNumero;
	}
		

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
	@Override
	public int hashCode() {
        int hash = 3;
                
        hash = 37 * hash + Objects.hashCode(this.cuentaOrgen);
        hash = 37 * hash + Objects.hashCode(this.cuentaDestino);
        hash = 37 * hash + Objects.hashCode(this.arcCodigoProceso);
        hash = 37 * hash + Objects.hashCode(this.regArcNumero);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TrasnferenciaPK que se pasa
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
        final TrasnferenciaPK other = (TrasnferenciaPK) obj;
        
        
        if (!Objects.equals(this.cuentaOrgen, other.cuentaOrgen)) {
            return false;
        }
        
        if (!Objects.equals(this.cuentaDestino, other.cuentaDestino)) {
            return false;
        }
        
        if (!Objects.equals(this.arcCodigoProceso, other.arcCodigoProceso)) {
            return false;
        }
        
        return Objects.equals(this.regArcNumero, other.regArcNumero);
                
    }
    
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
     @Override
     public String toString(){
     	StringBuilder cadena = new StringBuilder();
	     cadena.append("cuentaOrgen");
		 cadena.append(this.cuentaOrgen);
	 	cadena.append(", ");
         
	     cadena.append("cuentaDestino");
		 cadena.append(this.cuentaDestino);
	 	cadena.append(", ");
         
	     cadena.append("arcCodigoProceso");
		 cadena.append(this.arcCodigoProceso);
	 	cadena.append(", ");
         
	     cadena.append("regArcNumero");
		 cadena.append(this.regArcNumero);
         
     	return cadena.toString(); 
     }

} 
