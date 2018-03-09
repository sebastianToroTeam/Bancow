package com.asesoftware.bancow.modelo.excepciones;

/**
 * Excepción lanzada cuando se produce en error en alguno de los métodos de la
 * aplicación que hacen uso de reflection.
 * 
 * @author GeneradorCRUD
 */
public class ReflectionException extends RuntimeException {

    public ReflectionException (String message) {
        super (message);
    }

    public ReflectionException (Throwable cause) {
        super (cause);
    }

    public ReflectionException (String message, Throwable cause) {
        super (message, cause);
    }
}
