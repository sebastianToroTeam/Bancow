
package com.asesoftware.bancow.modelo.utils;

/**
 * Clase con constantes consultadas por diferentes clases de la aplicación
 * 
 * @author GeneradorCRUD
 */
public class UtilConstantes {	    
    
    public static final String SEPARADOR_HTTP_ORDER_BY = "$";    
    public static final String CARACTER_DE_ESCAPE = "\\";    
    public static final String SEPARADOR_PARAMETROS_CONSULTA = "&";    
    
    //ManejadorCrud
    public static final String NULL_VALUE = "NULL";
    public static final String NOT_NULL_VALUE = "NOT NULL";
    
    //UtilReflection
    public static final String LONG_PRIMITIVE = "long";
    public static final String INT_PRIMITIVE = "int";
    
    //CriteriaSearch
    
    public static final String TYPESAFE_QUERY = "TypesafeQuery";
    
    public static final String TQ_CONDITIONS = "Conditions";
    public static final String TQ_ORDERING = "Ordering";
    public static final String TQ_GROUPING = "Grouping";
    
    
    public static final String EXPRESSION_METHODS = "ExpressionMethods";
    public static final String CRITERIA_METHODS = "CriteriaMethods";
    public static final String COMPOUND_METHODS = "CompoundMethods";
    
    public static final String EX_IS_NULL = "isNull";
    public static final String EX_IS_NOT_NULL = "isNotNull";
    public static final String EX_IN = "in";
    
    public static final String CR_EQUAL = "equal";
    public static final String CR_NOT_EQUAL = "notEqual";
    public static final String CR_GREATER_THAN = "gt";
    public static final String CR_GREATER_EQUAL = "ge";
    public static final String CR_LESS_THAN = "lt";
    public static final String CR_LESS_EQUAL = "le";
    public static final String CR_BETWEEN = "between";
    public static final String CR_LIKE = "like";
	
    public static final String CM_AND = "and";
    public static final String CM_OR = "or";
    public static final String CM_NOT = "not";
    
    public static final String OR_ASC = "asc";
    public static final String OR_DESC = "desc";
    
    private UtilConstantes(){}
    
}
