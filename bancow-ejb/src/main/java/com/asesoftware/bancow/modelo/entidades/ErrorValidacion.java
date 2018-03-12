/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoftware.bancow.modelo.entidades;

import java.math.BigDecimal;

/**
 *
 * @author Asesoftware
 */
public class ErrorValidacion  {

    private BigDecimal codigo;
    private String descripcion;
    private BigDecimal codigoProceso;
    private Integer NumeroRegistro;

    public ErrorValidacion() {
        codigo = BigDecimal.ZERO;
        descripcion = "";
        codigoProceso = BigDecimal.ZERO;
        NumeroRegistro = 1;
    }

    public ErrorValidacion(BigDecimal codigo, String descripcion, BigDecimal codigoProceso, Integer NumeroRegistro) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.codigoProceso = codigoProceso;
        this.NumeroRegistro = NumeroRegistro;
    }    
    
    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(BigDecimal codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public Integer getNumeroRegistro() {
        return NumeroRegistro;
    }

    public void setNumeroRegistro(Integer NumeroRegistro) {
        this.NumeroRegistro = NumeroRegistro;
    }
    
    
    
}
