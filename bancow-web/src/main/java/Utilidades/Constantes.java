/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author storo
 */
public interface Constantes {
    //encabezado
    public static final String  encabezadoField = "Encabezado";    
    public static final String  detalleField = "Detalle"; 
    public static final String  TipoErroFormatoField = "Formato";
    public static final String  TipoErroValortoField = "Valor";
    //Detalle
    public static final String  DetalleCampo1Field = "";
    public static final String  DetalleCampo2Field = "";
    public static final String  DetalleCampo3Field = "";
    //Errores generales
    public static final String ERROR_NEG_001 = "ERROR GRAVE, al leer campo nulo ";
    public static final String ERROR_NEG_1 = "Error en el %s del archivo, en la fila %d , tipo error %s, Error: %s ";
    public static final String ERROR_NEG_2 = "Error en el %s del archivo, en la fila %d , tipo error %s, Valor: %s ";
    
    //ftp
    public static final String SERVER = "192.168.3.27";
    public static final String USER = "bancow";
    public static final String PASSWORD = "b4nc0w2018*";
    public static final String RUTA_SERVER =  "/home/bancow/cdump";
    
}
