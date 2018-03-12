/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoftware.bancow.modelo.entidades;

/**
 *
 * @author Asesoftware
 */
public class DetDominio {
    private String valor;
    private String descripcion;
    private String estado;
    private String codigoDominio;
    
    public DetDominio(){
        valor = "";
        descripcion = "";
        estado = "";
        codigoDominio = "";
    }
    
    public DetDominio(String valor, String descripcion, 
            String estado, String codigoDominio){
        this.valor = valor;
        this.descripcion = descripcion;
        this.estado = estado;
        this.codigoDominio = codigoDominio;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoDominio() {
        return codigoDominio;
    }

    public void setCodigoDominio(String codigoDominio) {
        this.codigoDominio = codigoDominio;
    }
    
    
}
