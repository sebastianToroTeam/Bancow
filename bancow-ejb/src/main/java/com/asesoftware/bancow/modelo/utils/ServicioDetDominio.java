
package com.asesoftware.bancow.modelo.utils;

/**
 *
 * @author Asesoftware
 */

import com.asesoftware.bancow.modelo.entidades.DetDominio;
import com.asesoftware.bancow.modelo.entidades.EncDominio;
import com.asesoftware.bancow.modelo.manejadores.ManejadorEncDominio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ServicioDetDominio {
    
    @EJB
    ManejadorEncDominio manejador;
    
    private static Map<String,DetDominio> dominios;
    private Map<String,EncDominio> encabezadosDominios;
    
    @PostConstruct
    private void init() {
        dominios = new HashMap<>();
        List<DetDominio> dominiosList = manejador.obtenerTodosDominios();
        for(DetDominio d : dominiosList){
            dominios.put(d.getValor(), d);
        }
    }
    
    public static DetDominio obtenerDominioPorValor(String valor){
        return dominios.get(valor);
    }
}
