package com.asesoftware.bancow.modelo.manejadores.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.Embeddable;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.asesoftware.bancow.modelo.utils.UtilConstantes;

public abstract class ManejadorCrud<T,U> implements IManejadorCrud<T,U> {

    /**
     * a imprimir logs...
     */
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ManejadorCrud.class.getName());
    
    public static final Integer IGNORAR_PARAMETRO_CONSULTA = -1;
    
    @EJB
    protected ManejadorPersistencia<T> mp;
    
    private Class<T> claseEntidad;
    
	private CriteriaBuilder cb;
    private CriteriaQuery<T> cq;
    private Root<T> root;

    /**
     * @param claseEntidad Clase de la entidad T
     */
    public ManejadorCrud(Class<T> claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    /**
     * {@inheritDoc}
     * @param pId {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public T buscar(U pId) {
        return mp.find(getClaseEntidad(), pId);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public T crear() {
        T instancia = null;
        try {
            instancia = getClaseEntidad().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            logger.error(ex);
        }        
        return instancia;
        
    }



    /**
     * Metodo que se ejecuta antes de crear un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void preCrear() {

    }

    /**
     * Metodo que se ejecuta despues de crear un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void postCrear() {

    }

    /**
     * {@inheritDoc}
     * @param pData {@inheritDoc} 
     */
    @Override
    public void crear(T pData) {
        preGuardar();
        preCrear();
        mp.create(pData);
        postCrear();
        postGuardar();
    }

    /**
     * Metodo que se ejecuta antes de actualizar un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void preActualizar() {

    }

    /**
     * Metodo que se ejecuta despues de crear un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void postActualizar() {

    }

    /**
     * {@inheritDoc}
     * @param pData {@inheritDoc}
     */
    @Override
    public void actualizar(T pData) {
        preGuardar();
        preActualizar();
        mp.update(pData);
        postActualizar();
        postGuardar();
    }

    /**
     * Metodo que se ejecuta antes de eliminar un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void preEliminar() {

    }

    /**
     * Metodo que se ejecuta despues de eliminar un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void postEliminar() {

    }

    /**
     * {@inheritDoc}
     * @param pData {@inheritDoc}
     */
    @Override
    public void eliminar(T pData) {
        
        preEliminar();
        mp.delete(pData);
        postEliminar();
        
    }

    /**
     * {@inheritDoc}
     * @param pId {@inheritDoc}
     */
    @Override
    public void eliminarPorId(U pId) {
        
        preEliminar();
        mp.delete(getClaseEntidad(), pId);
        postEliminar();
        
    }


    /**
     * Metodo que se ejecuta antes de crear o actualizar un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void preGuardar() {
    }

    /**
     * Metodo que se ejecuta despues de crear o actualizar un registro en la base de datos y 
     * en el que la clase que extienda esta clase puede anadir funcionalidad 
     * personalizada.
     */
    public void postGuardar() {
    }    

    /**
     * @return the claseEntidad
     */
    public Class<T> getClaseEntidad() {
        return claseEntidad;
    }

    /**
     * @param claseEntidad the claseEntidad to set
     */
    public void setClaseEntidad(Class<T> claseEntidad) {
        this.claseEntidad = claseEntidad;
    }
    
 
    /**
     * 
     */
    public List<T> consultarPorCriteria(SearchExpression search) {
    	
    	this.initCriteriaBuilder();        
        this.buildCriteriaExpression(search);
        TypedQuery<T> tq = mp.getTypedQuery(cq);
        List<T> results = tq.getResultList();
        
        return results;
    }
    /**
     * 
     */
    private void initCriteriaBuilder() {
        this.cb = mp.getCriteriaBuilder();
        this.cq = cb.createQuery(this.claseEntidad);  
        this.root = cq.from(this.claseEntidad);
    }
    /**
     * 
     * @param search
     */
    private void buildCriteriaExpression(SearchExpression search) {

    	List<Predicate> predicates = this.buildConditions(search);
    	List<Order> orders = this.buildOrdering(search);
    	    	
        cq.select(root);
        cq.where(predicates.toArray(new Predicate[]{}));
        cq.orderBy(orders.toArray(new Order[]{}));
    }
    /**
     * 
     * @param search
     * @return
     */
    private List<Predicate> buildConditions(SearchExpression search) {
    	
    	List<Predicate> predicates =  new ArrayList<Predicate>();
		
		Object entry = search.getValues().get(UtilConstantes.TQ_CONDITIONS);
    	if (entry != null) {
    		@SuppressWarnings("unchecked")
			List<SearchExpressionCriteria> list = (List<SearchExpressionCriteria>)entry;
    		Iterator<SearchExpressionCriteria> it = list.iterator();
    		while(it.hasNext()) {
    			SearchExpressionCriteria se = (SearchExpressionCriteria)it.next();
				switch (se.getCompound()) {
    				case UtilConstantes.CM_AND :
    					predicates.add(cb.and(this.getCriteriaMethod(se.getField(), se)));
    					break;
    				case UtilConstantes.CM_OR :
    					predicates.add(cb.or(this.getCriteriaMethod(se.getField(), se)));
    					break;
    				case UtilConstantes.CM_NOT :
    					predicates.add(cb.not(this.getCriteriaMethod(se.getField(), se)));
    					break;
    				default : predicates.add(cb.and(this.getCriteriaMethod(se.getField(), se)));
    			}
	    	}
    	}

		return predicates;
    }
    /**
     * 
     * @param key
     * @param se
     * @return
     */
    private Predicate getCriteriaMethod(String key, SearchExpressionCriteria se) {
        Predicate pd;
        SingularAttribute sa = root.getModel().getId(root.getModel().getIdType().getJavaType());
        Path p = root;
        Class c = sa.getType().getJavaType();
        String[] separado = se.getField().split("\\.");
        String nombreCampo = separado[separado.length-1];
        if(sa.getType().getJavaType().isAnnotationPresent(Embeddable.class) && classHasField(c,nombreCampo))
            p = root.<Object>get(sa.getName());
        switch (se.getMethod()) {
            case UtilConstantes.CR_EQUAL:
                pd = cb.equal(p.<Object>get(nombreCampo), se.getValues().get(0).toString());
                break;
            case UtilConstantes.CR_NOT_EQUAL:
                pd = cb.notEqual(p.<String>get(nombreCampo), se.getValues().get(0).toString());
                break;
            case UtilConstantes.CR_GREATER_THAN:
                pd = cb.greaterThan(p.<String>get(nombreCampo), se.getValues().get(0).toString());
                break;
            case UtilConstantes.CR_GREATER_EQUAL:
                pd = cb.greaterThanOrEqualTo(p.<String>get(nombreCampo), se.getValues().get(0).toString());
                break;
            case UtilConstantes.CR_LESS_THAN:
                pd = cb.lessThan(p.<String>get(nombreCampo), se.getValues().get(0).toString());
                break;
            case UtilConstantes.CR_LESS_EQUAL:
                pd = cb.lessThanOrEqualTo(p.<String>get(nombreCampo), se.getValues().get(0).toString());
                break;
            case UtilConstantes.CR_BETWEEN:
                pd = cb.between(p.<Integer>get(nombreCampo), Integer.parseInt(se.getValues().get(0).toString()), Integer.parseInt(se.getValues().get(1).toString()));
                break;
            case UtilConstantes.CR_LIKE:
                pd = cb.like(p.<String>get(nombreCampo), "%" + se.getValues().get(0).toString() + "%");
                break;
            case UtilConstantes.EX_IS_NULL:
                pd = p.<String>get(nombreCampo).isNull();
                break;
            case UtilConstantes.EX_IS_NOT_NULL:
                pd = p.<String>get(nombreCampo).isNotNull();
                break;
            case UtilConstantes.EX_IN:
                pd = p.<String>get(nombreCampo).in(se.getValues());
                break;
            default:
                pd = p.<String>get(nombreCampo).isNotNull();
        }
        return pd;
    }
    
    private List<Order> buildOrdering(SearchExpression search) {

        List<Order> orders = new ArrayList<Order>();
        //Inicio Daniel
        SingularAttribute sa = root.getModel().getId(root.getModel().getIdType().getJavaType());

        //Fin Daniel
        Object entry = search.getValues().get(UtilConstantes.TQ_ORDERING);
        if (entry != null) {
            @SuppressWarnings("unchecked")
            List<SearchExpressionOrder> list = (List<SearchExpressionOrder>) entry;
            Iterator<SearchExpressionOrder> it = list.iterator();
            while (it.hasNext()) {
                SearchExpressionOrder se = (SearchExpressionOrder) it.next();
                Path p = root;
                Class c = sa.getType().getJavaType();
                String[] separado = se.getField().split("\\.");
                String nombreCampo = separado[separado.length - 1];
                if (sa.getType().getJavaType().isAnnotationPresent(Embeddable.class) && classHasField(c, nombreCampo)) {
                    p = root.<Object>get(sa.getName());
                }
                
                switch (se.getOrder()) {
                    case UtilConstantes.OR_ASC:
                        orders.add(cb.asc(p.<String>get(nombreCampo)));
                        break;
                    case UtilConstantes.OR_DESC:
                        orders.add(cb.desc(p.<String>get(nombreCampo)));
                        break;
                    default:
                        orders.add(cb.asc(p.<String>get(nombreCampo)));

                }
            }
        }
        return orders;
    }
    
    private boolean classHasField(Class c, String field){
        try{
            c.getDeclaredField(field);
            return true;
        } catch (NoSuchFieldException | SecurityException ex) {
            return false;
        }
    }

}
