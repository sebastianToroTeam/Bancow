
package com.asesoftware.bancow.tests.ejb.manejadores;

import java.math.BigDecimal;
import static com.asesoftware.bancow.modelo.entidades.Trasnferencia.ENTIDAD_TRASNFERENCIA_VALOR;
import static com.asesoftware.bancow.modelo.entidades.Trasnferencia.ENTIDAD_TRASNFERENCIA_FECHA;
import java.sql.Timestamp;
import com.asesoftware.bancow.modelo.entidades.Trasnferencia;
import com.asesoftware.bancow.modelo.entidades.TrasnferenciaPK;
import static com.asesoftware.bancow.modelo.entidades.Trasnferencia.ENTIDAD_TRASNFERENCIA_PK_CUENTA_ORGEN;
import static com.asesoftware.bancow.modelo.entidades.Trasnferencia.ENTIDAD_TRASNFERENCIA_PK_CUENTA_DESTINO;
import static com.asesoftware.bancow.modelo.entidades.Trasnferencia.ENTIDAD_TRASNFERENCIA_PK_ARC_CODIGO_PROCESO;
import static com.asesoftware.bancow.modelo.entidades.Trasnferencia.ENTIDAD_TRASNFERENCIA_PK_REG_ARC_NUMERO;
import com.asesoftware.bancow.modelo.manejadores.ManejadorTrasnferencia;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpression;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionCriteria;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionOrder;
import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorPersistencia;
import com.asesoftware.bancow.modelo.utils.UtilConstantes;
import static com.asesoftware.bancow.modelo.utils.UtilOperaciones.convertirListaObjetosAString;
import com.asesoftware.bancow.tests.ejb.excepciones.TestException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.apache.commons.beanutils.BeanUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
	
/**
 * Pruebas de los métodos expuestos por el ManejadorTrasnferencia
 *
 * @author GeneradorCRUD
 */
@RunWith(Arquillian.class)
public class ManejadorTrasnferenciaTest {

public static final String CM_AND = "and";
    public static final String CM_OR = "or";
    public static final String CM_NOT = "not";
    public static final String EX_IS_NULL = "isNull";
    public static final String EX_IS_NOT_NULL = "isNotNull";
    public static final String EX_IN = "in";
    public static final String CR_EQUAL = "equal";
    public static final String CR_NOT_EQUAL = "notEqual";
    public static final String CR_GREATER_THAN = "gt";
    public static final String CR_GREATER_EQUAL = "ge";
    public static final String CRI_GREATER_THAN = "Less than";
    public static final String CRI_GREATER_EQUAL = "Less than or equal";
    public static final String CR_BETWEEN = "between";
    public static final String CR_LIKE = "like";
    public static final String OR_ASC = "asc";
    public static final String OR_DESC = "desc";
	
	public static final String ENTIDAD_TRASNFERENCIA_PK_CUENTA_ORGEN = "cuentaOrgen";
	public static final String ENTIDAD_TRASNFERENCIA_PK_CUENTA_DESTINO = "cuentaDestino";
	public static final String ENTIDAD_TRASNFERENCIA_PK_ARC_CODIGO_PROCESO = "arcCodigoProceso";
	public static final String ENTIDAD_TRASNFERENCIA_PK_REG_ARC_NUMERO = "regArcNumero";
	public static final String ENTIDAD_TRASNFERENCIA_VALOR = "valor";
	public static final String ENTIDAD_TRASNFERENCIA_FECHA = "fecha";
    private static final String[] ATRIBUTOS_ENTIDAD_TRASNFERENCIA
            = {ENTIDAD_TRASNFERENCIA_PK_CUENTA_ORGEN, ENTIDAD_TRASNFERENCIA_FECHA, ENTIDAD_TRASNFERENCIA_PK_CUENTA_DESTINO, ENTIDAD_TRASNFERENCIA_PK_REG_ARC_NUMERO, ENTIDAD_TRASNFERENCIA_VALOR, ENTIDAD_TRASNFERENCIA_PK_ARC_CODIGO_PROCESO};
	
    public static final String DEPLOY = "Prueba"; 
    //Debe ser mayor o igual a 2
    private static final int REGISTROS_A_CREAR = 10;
    
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ManejadorTrasnferenciaTest.class.getName());
    
    //Arreglo con los posibles valores de descripción fijos a insertar en el atributo descripción para la prueba de consultaLista
	private static final BigDecimal[] VALORES_VALOR = {new BigDecimal(111.11d), new BigDecimal(222.22d), new BigDecimal(333.33d)};
    
    @Inject
    private ManejadorTrasnferencia manejador;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    //Generador de datos Podam que usa el default Random Data Provider Strategy
    private final PodamFactory factory = new PodamFactoryImpl(); 
    
    //Lista de instancias de Trasnferencia generadas aleatoriamente por PODAM
    private final List<Trasnferencia> data = new ArrayList<>();   
    
    // protected region atributos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region atributos adicionales end

    
    @Deployment
    public static JavaArchive createDeployment() {
        
        return ShrinkWrap.create(JavaArchive.class, DEPLOY + ".jar")
                .addPackage(ManejadorTrasnferencia.class.getPackage())
                .addPackage(ManejadorPersistencia.class.getPackage())
                .addPackage(Trasnferencia.class.getPackage())
                .addPackage(UtilConstantes.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(new FileAsset(new File("src/test/resources/META-INF/test-derby-persistence.xml")), "persistence.xml");
    }
    
    @Before
    public void configTest() throws Exception{
        logger.debug("Configurando Test " + ManejadorTrasnferenciaTest.class.getName());
        clearData();
        insertData();
        startTransaction();
    }
    
    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }
  	
  		public SearchExpression obtenerSearchExpresion(List<SearchExpressionCriteria> listaC, List<SearchExpressionOrder> listaO) {
        SearchExpression searchExpression = new SearchExpression(new ArrayList<String>());
        if (listaC!=null  && !listaC.isEmpty()) {
            searchExpression.setObject(UtilConstantes.TQ_CONDITIONS, listaC);
        }
        if (listaO!=null && !listaO.isEmpty()) {
            searchExpression.setObject(UtilConstantes.TQ_ORDERING, listaO);
        }
        return searchExpression;
  		}
    @Test
    public void eliminarTest() {
        Trasnferencia entidad = data.get(0);
        manejador.eliminar(entidad);
        Trasnferencia deleted = em.find(Trasnferencia.class, entidad.getTrasnferenciaPK());
        
        Assert.assertNull(deleted);        
    }
    
    @Test
    public void eliminarPorIdTest() {
        Trasnferencia entidad = data.get(0);
        manejador.eliminarPorId(entidad.getTrasnferenciaPK());
        Trasnferencia deleted = em.find(Trasnferencia.class,entidad.getTrasnferenciaPK());
        Assert.assertNull(deleted);        
    }

    @Test
    public void crearTest() {     
        Trasnferencia entidad = generarTrasnferencia();
        manejador.crear(entidad);
        Trasnferencia creada = em.find(Trasnferencia.class, entidad.getTrasnferenciaPK());        
        Assert.assertNotNull(creada);                
    }
    
    @Test
    public void actualizarTest() {
        Trasnferencia entidad = data.get(0);
        TrasnferenciaPK idEntidad = entidad.getTrasnferenciaPK();
        Trasnferencia entidad2 = data.get(1);
        copiarPropiedades(entidad, entidad2);
        entidad.setTrasnferenciaPK(idEntidad);
        manejador.actualizar(entidad);
        Trasnferencia entidadDB = em.find(Trasnferencia.class, idEntidad);
        Assert.assertEquals(entidadDB,entidad);
    }
    
    @Test
    public void consultarTodosTest() {
        List<Trasnferencia> listaConsulta = manejador.consultarPorCriteria(obtenerSearchExpresion(null, null));

        Assert.assertEquals(listaConsulta.size(), data.size());
        for(Trasnferencia instancia : listaConsulta){
            if(!data.contains(instancia)){
                Assert.fail("Se consulto un elemento que no estaba en la base de datos");
            }
        }
                    
    }
    
    @Test
    public void consultarConFiltroPKTest() {
        Trasnferencia entidad = data.get(0);
        
        SearchExpression searchExpression;
        
        List<SearchExpressionCriteria> filtros = new ArrayList<>();
		
		ArrayList<String> values1 = new ArrayList<>();
		values1.add(entidad.getTrasnferenciaPK().getCuentaOrgen().toString());
		filtros.add(new SearchExpressionCriteria(ENTIDAD_TRASNFERENCIA_PK_CUENTA_ORGEN, CR_EQUAL, CM_AND, values1));		
		ArrayList<String> values2 = new ArrayList<>();
		values1.add(entidad.getTrasnferenciaPK().getCuentaDestino().toString());
		filtros.add(new SearchExpressionCriteria(ENTIDAD_TRASNFERENCIA_PK_CUENTA_DESTINO, CR_EQUAL, CM_AND, values1));		
		ArrayList<String> values3 = new ArrayList<>();
		values3.add(entidad.getTrasnferenciaPK().getArcCodigoProceso().toString());
		filtros.add(new SearchExpressionCriteria(ENTIDAD_TRASNFERENCIA_PK_ARC_CODIGO_PROCESO, CR_EQUAL, CM_AND, values1));		
		ArrayList<String> values4 = new ArrayList<>();
		values4.add(entidad.getTrasnferenciaPK().getRegArcNumero().toString());
		filtros.add(new SearchExpressionCriteria(ENTIDAD_TRASNFERENCIA_PK_REG_ARC_NUMERO, CR_EQUAL, CM_AND, values1));		
		searchExpression = obtenerSearchExpresion(filtros, null);
	    List<Trasnferencia> listaConsulta = manejador.consultarPorCriteria(searchExpression);
        
    	 Assert.assertTrue(entidad.equals(listaConsulta.get(0)));
    	}
    	
 	
    	
    @Test
    public void consultarConDosFiltrosTest() {        
        
        
        Trasnferencia entidad = data.get(0);
        
        SearchExpression searchExpression;
        
        List<SearchExpressionCriteria> filtros = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        values.add(entidad.getValor().toString());
        filtros.add(new SearchExpressionCriteria(ENTIDAD_TRASNFERENCIA_VALOR, CR_EQUAL, CM_AND, values));
        
        ArrayList<String> values1 = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      	values1.add(sdf.format(entidad.getFecha()));
        filtros.add(new SearchExpressionCriteria(ENTIDAD_TRASNFERENCIA_FECHA, CR_EQUAL, CM_AND, values1));
	

		searchExpression = obtenerSearchExpresion(filtros, null);
	    List<Trasnferencia> listaConsulta = manejador.consultarPorCriteria(searchExpression);
    	Assert.assertEquals(entidad,listaConsulta.get(0));
    	}
    
    @Test
    public void consultarConOrdenamientoTest() {		
		SearchExpression searchExpression;
        List<Trasnferencia> ordenarLista = data;
        Collections.sort(ordenarLista, new ValorComparator());
        List<SearchExpressionOrder> ordenar = new ArrayList<>();
        ordenar.add(new SearchExpressionOrder(ENTIDAD_TRASNFERENCIA_VALOR, OR_ASC));
        searchExpression = obtenerSearchExpresion(null, ordenar);
        List<Trasnferencia> consultarLista = manejador.consultarPorCriteria(searchExpression);
        
        Assert.assertEquals(consultarLista, ordenarLista);
    }
	
    private void insertData()  {        
        try {            
            utx.begin();
            em.joinTransaction();
            //Luego, se generan 5 datos de prueba diferentes
            for (int i = 0; i < REGISTROS_A_CREAR; i++) {            
                Trasnferencia entity = generarTrasnferencia();                       
                //Persiste el objeto en base de datos
                em.persist(entity);
                //Se añade a la lista del oráculo
                data.add(entity);
                logger.debug("Se inserto Trasnferencia: " + entity.toString());
            }
            utx.commit();
            // clear the persistence context (first-level cache)
            em.clear();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException 
                    | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
             logger.debug(ex.getMessage());
             throw new TestException("Se produjo un error limpiando la tabla de la base de datos");
        }
        
    }
    
    /**
     * Genera una nueva instancia de Trasnferencia cuyo id no se encuentre ya en la base de datos.
     * 
     * @param factory La fábrica podam para generar POJOS
     * @return Una instancia de categoría
     */
    private Trasnferencia generarTrasnferencia(){
        Trasnferencia entity = null;
        while(entity==null){
            entity = factory.manufacturePojo(Trasnferencia.class);
            // protected region atributos adicionales on begin
            // Escriba en esta sección sus modificaciones

            // protected region atributos adicionales end
            //Este valor se setea para el test de agrupamiento
            entity.setValor(obtenerValorAleatoria());
            if( em.find(Trasnferencia.class, entity.getTrasnferenciaPK())!=null ){
                entity = null;
                logger.debug("Se genero id repetido. Reintentando creación");
            }            
        } 
        return entity;        
    }
    
    /**
     * Elimina todos los registros en base de datos de la tabla correspondiente a
     * la entidad Trasnferencia, también elimina la lista correspondiente a estos
     * registros que se almacenan en esta instancia de test.
     */
    private void clearData() throws TestException {        
        try {
            utx.begin();
            em.joinTransaction();
            em.createQuery("DELETE FROM Trasnferencia").executeUpdate();
            data.clear();
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            data.clear();
            logger.debug(e.getMessage());
            throw new TestException("Se produjo un error limpiando la tabla de la base de datos");
        }
        
    }
    
    /**
     * Copia el valor que contienen los atributos del objeto fuente 
     * a los atributos del objeto destino cuyos nombres sean exactamente iguales.
     * Los atributos que no coinciden se omiten (Se dejan tal cual como estaban 
     * en el objeto destino).
     * 
     * @param destino objeto al que se le van a setear los valores de sus atributos
     * @param fuente objeto del que se copian los valores de los atributos
     */
    protected void copiarPropiedades(Object destino, Object fuente) {
        
        try {
            BeanUtils.copyProperties(destino, fuente);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.error(ex);
            throw new TestException("Hubo un problema copiando info de entidades.");
        }
        
    }
    
    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    private static class ValorComparator implements Comparator<Trasnferencia> {
    
        @Override
        public int compare(Trasnferencia instancia1, Trasnferencia instancia2) {
            return instancia1.getValor().compareTo(instancia2.getValor());
        }
    }
    
	private static BigDecimal  obtenerValorAleatoria(){
        Random generator = new Random();
        int index = generator.nextInt(VALORES_VALOR.length);
        
        return VALORES_VALOR[index];
    }
       
    
    // protected region atributos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region atributos adicionales end
}

