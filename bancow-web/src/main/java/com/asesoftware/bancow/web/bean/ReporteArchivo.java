/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoftware.bancow.web.bean;

import com.asesoftware.bancow.modelo.entidades.ArchivoProcesado;
import com.asesoftware.bancow.modelo.entidades.DetDominio;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivo;
import com.asesoftware.bancow.modelo.entidades.RegistroArchivoPK;
import com.asesoftware.bancow.modelo.manejadores.utils.ManejadorCrud;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpression;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionCriteria;
import com.asesoftware.bancow.modelo.manejadores.utils.SearchExpressionOrder;
import com.asesoftware.bancow.modelo.utils.ServicioDetDominio;
import com.asesoftware.bancow.modelo.utils.UtilConstantes;
import com.asesoftware.bancow.negocio.NegocioArchivoProcesado;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uk.co.jemos.podam.annotations.PodamExclude;
import com.asesoftware.bancow.web.aplicacion.FilterService;
import java.util.Arrays;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;




/**
 *
 * @author Asesoftware
 */
@ManagedBean
@ViewScoped
public class ReporteArchivo implements Serializable{
    
    
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private List<SearchExpressionCriteria> searchExpressionList;
    private List<SearchExpressionOrder> searchOrderList;
    private SearchExpression searchExpression;
    private List<RegistroArchivo> registroArchivos;
    protected ManejadorCrud mc;
    @EJB
    private FilterService filterService;
    
        private static final String iTextExampleImage = "C:\\Users\\storo\\Documents\\Banco w\\bancow-logo.png";
    


    @EJB
    private NegocioArchivoProcesado negocioArchivoProcesado;

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ArchivoProcesadoManagedBean.class.getName());

    private ArchivoProcesado archivoProcesado;
    private List<ArchivoProcesado> archivoProcesadoList;
    private List<String> fieldList;
    private List<SelectItem> fieldDomainList;


    private String tipoProcesoSeleccionado;
    private BigDecimal filtroId;
    private Date filtroFechaIni;
    private Date filtroFechaFin;

    @PostConstruct
    public void init() {

        this.setFieldDomainList();
        this.resetFilter();
        this.search();
    }
    
    public void addSearchExpressionCriteria() {
        this.searchExpressionList.add(new SearchExpressionCriteria());
    }

    /**
     *
     */
    public void addSearchOrder() {
        this.searchOrderList.add(new SearchExpressionOrder());
    }

    /**
     *
     * @param sec
     */
    public void removeSearchExpressionCriteria(SearchExpressionCriteria sec) {
        this.searchExpressionList.remove(sec);
    }

    /**
     *
     * @param seo
     */
    public void removeSearchOrder(SearchExpressionOrder seo) {
        this.searchOrderList.remove(seo);
    }

    /**
     *
     */
    public void search() {
        construirFiltros();
        try {
            if (!this.searchExpressionList.isEmpty()) {
                this.searchExpression.setObject(UtilConstantes.TQ_CONDITIONS, this.searchExpressionList);
            }
            if (!this.searchOrderList.isEmpty()) {
                this.searchExpression.setObject(UtilConstantes.TQ_ORDERING, this.searchOrderList);
            }
            this.archivoProcesadoList = this.consultar(this.searchExpression);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda", "Mostrando " + this.archivoProcesadoList.size() + " registro(s).");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void reset() {
        this.resetFilter();
        this.search();
    }

    /**
     *
     * @param search
     * @return
     */
    public List<ArchivoProcesado> consultar(SearchExpression search) {
        return negocioArchivoProcesado.consultar(search);
    }

    /**
     *
     */
    private void resetFilter() {
        if (this.searchExpressionList == null || this.searchExpressionList.size() > 0) {
            this.searchExpressionList = new ArrayList<SearchExpressionCriteria>();
        }
        if (this.searchOrderList == null || this.searchOrderList.size() > 0) {
            this.searchOrderList = new ArrayList<SearchExpressionOrder>();
        }
        this.searchExpression = new SearchExpression(
                new ArrayList<String>(this.filterService.getTypesafeQueryStringList()));

    }

    /**
     *
     */
    private void setFieldDomainList() {
        this.fieldList = new ArrayList<String>(Arrays.asList(ArchivoProcesado.getAtributosEntidadArchivoProcesado()));
        this.fieldDomainList = new ArrayList<SelectItem>();

        // Iterar //
        this.fieldDomainList.add(new SelectItem("codigoProceso", "codigoProceso"));
        this.fieldDomainList.add(new SelectItem("tipoProceso", "tipoProceso"));
        this.fieldDomainList.add(new SelectItem("nombreArchivo", "nombreArchivo"));
        this.fieldDomainList.add(new SelectItem("estado", "estado"));
        this.fieldDomainList.add(new SelectItem("fechaEjecucion", "fechaEjecucion"));
        this.fieldDomainList.add(new SelectItem("cantidadRegistros", "cantidadRegistros"));
        this.fieldDomainList.add(new SelectItem("valorTotal", "valorTotal"));
        this.fieldDomainList.add(new SelectItem("transferenciasExitosas", "transferenciasExitosas"));
        this.fieldDomainList.add(new SelectItem("transferenciasFallidas", "transferenciasFallidas"));
        this.fieldDomainList.add(new SelectItem("convenioCodigo", "convenioCodigo"));

    }

    public void createArchivoProcesadoWithForm() {
    }

    /**
     *
     * @param archivoProcesado
     */
    public void deleteArchivoProcesado(ArchivoProcesado archivoProcesado) {
        try {
            negocioArchivoProcesado.delete(archivoProcesado);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminar ArchivoProcesado", "Idenfitificado por: " + "codigoProceso: " + archivoProcesado.getCodigoProceso());
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.search();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No fue posible eliminar el registro", "Idenfitificado por: " + "codigoProceso: " + archivoProcesado.getCodigoProceso());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     *
     * @param summary
     * @param detail
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *
     * @param event
     */
    public void onRowEdit(RowEditEvent event) {
        try {
            FacesMessage msg = new FacesMessage("ArchivoProcesado Editado");
            negocioArchivoProcesado.edit((ArchivoProcesado) event.getObject());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro no pudo ser editado", "Corrija los datos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     *
     * @param event
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("ArchivoProcesado Cancelado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /*
     * Getters AND Setters
     */
    public List<ArchivoProcesado> getArchivoProcesadoList() {
        return archivoProcesadoList;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

    public List<SelectItem> getFieldDomainList() {
        return this.fieldDomainList;
    }

    public void setFieldDomainList(List<SelectItem> fieldDomainList) {
        this.fieldDomainList = fieldDomainList;
    }

    public List<SearchExpressionCriteria> getSearchExpressionList() {
        return searchExpressionList;
    }

    public void setSearchExpressionList(List<SearchExpressionCriteria> searchExpressionList) {
        this.searchExpressionList = searchExpressionList;
    }

    public List<SearchExpressionOrder> getSearchOrderList() {
        return searchOrderList;
    }

    public void setSearchOrderList(List<SearchExpressionOrder> searchOrderList) {
        System.out.println(searchOrderList);
        this.searchOrderList = searchOrderList;
    }

    public List<SelectItem> getMethods() {
        return this.filterService.getMethods();
    }

    public List<SelectItem> getCompoundMethods() {
        return this.filterService.getConditionsList(UtilConstantes.COMPOUND_METHODS);
    }

    public List<SelectItem> getOrderingList() {
        return this.filterService.getOrderingList();
    }

    public ArchivoProcesado getArchivoProcesado() {
        return archivoProcesado;
    }

    public void setArchivoProcesado(ArchivoProcesado archivoProcesado) {
        this.archivoProcesado = archivoProcesado;
    }

    public List<DetDominio> getTiposProceso() {
        return negocioArchivoProcesado.getTiposProceso();
    }

    public String getTipoProcesoSeleccionado() {
        return tipoProcesoSeleccionado;
    }

    public void setTipoProcesoSeleccionado(String tipoProcesoSeleccionado) {
        this.tipoProcesoSeleccionado = tipoProcesoSeleccionado;
    }

    public BigDecimal getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(BigDecimal filtroId) {
        this.filtroId = filtroId;
    }

    public Date getFiltroFechaIni() {
        return filtroFechaIni;
    }

    public void setFiltroFechaIni(Date filtroFechaIni) {
        this.filtroFechaIni = filtroFechaIni;
    }

    public Date getFiltroFechaFin() {
        return filtroFechaFin;
    }

    public void setFiltroFechaFin(Date filtroFechaFin) {
        this.filtroFechaFin = filtroFechaFin;
    }

    public void construirFiltros() {
        searchExpressionList.clear();
        if (tipoProcesoSeleccionado != null && !tipoProcesoSeleccionado.isEmpty()) {

            SearchExpressionCriteria tipo = new SearchExpressionCriteria();
            tipo.setCompound(UtilConstantes.CM_AND);
            tipo.setField("tipoProceso");
            tipo.setMethod(UtilConstantes.CR_EQUAL);
            List<String> values = new ArrayList<>();
            values.add(tipoProcesoSeleccionado);
            tipo.setValues(values);

            searchExpressionList.add(tipo);
        }

        if (filtroId != null) {
            SearchExpressionCriteria fid = new SearchExpressionCriteria();
            fid.setCompound(UtilConstantes.CM_AND);
            fid.setField("codigoProceso");
            fid.setMethod(UtilConstantes.CR_EQUAL);
            List<String> values = new ArrayList<>();
            values.add(filtroId.toString());
            fid.setValues(values);

            searchExpressionList.add(fid);
        }

        if (filtroFechaIni != null) {
            SearchExpressionCriteria ffini = new SearchExpressionCriteria();
            ffini.setCompound(UtilConstantes.CM_AND);
            ffini.setField("fechaEjecucion");
            ffini.setMethod(UtilConstantes.CR_GREATER_EQUAL);
            List<String> values = new ArrayList<>();
            values.add(new SimpleDateFormat("yyyy-MM-dd").format(filtroFechaIni));
            ffini.setValues(values);

            searchExpressionList.add(ffini);
        }

        if (filtroFechaFin != null) {
            SearchExpressionCriteria fffin = new SearchExpressionCriteria();
            fffin.setCompound(UtilConstantes.CM_AND);
            fffin.setField("fechaEjecucion");
            fffin.setMethod(UtilConstantes.CR_LESS_EQUAL);
            List<String> values = new ArrayList<>();
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(filtroFechaFin) + " 23:59:59";
            values.add(fecha);
            fffin.setValues(values);

            searchExpressionList.add(fffin);
        }
    }
    
    public void mostrarMensajes(ArchivoProcesado reg) {

    }
    
    public String obtenerDescripcionPorValor(String valor){
        DetDominio dd = ServicioDetDominio.obtenerDominioPorValor(valor);
        return dd.getDescripcion();
    }

    

    
      



    

    
    public  void createField(BigDecimal codigoProceso){
        System.out.println("Inicia com.asesoftware.bancow.web.bean.ReporteArchivo.createField()");        
        System.out.println("Codigo Proceso: "+codigoProceso);
        
        String file = "C:\\tmp\\GeneratePDFFileIText6.pdf";
        
        Document document = new Document();
        
        this.resetFilter();
        
         try {
 
            try {

                PdfWriter.getInstance(document, new FileOutputStream(file));

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No such file was found to generate the PDF "
                        + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }
            document.open();
            // We add metadata to PDF
            // Añadimos los metadatos del PDF
            document.addTitle("TRANSFERENCIAS MASIVAS ENTRE CUENTAS");
            document.addSubject("TRANSFERENCIAS");
            document.addKeywords("Banco w");
            document.addAuthor("Asesoftware");
            document.addCreator("Asesoftware");
            
            // First page
            // Primera página 
            //Chunk chunk = new Chunk("                                     TRANSFERENCIAS MASIVAS ENTRE CUENTAS", smallBold);
            //chunk.setBackground(BaseColor.GRAY);
            // Let's create de first Chapter (Creemos el primer capítulo)
            Chapter chapter = new Chapter("", Paragraph.ALIGN_CENTER);
           // chapter. = Element.ALIGN_CENTER;
            //chapter.setNumberDepth(0);
            float img = (float)22;
            Paragraph parafo = new Paragraph("                TRANSFERENCIAS MASIVAS ENTRE CUENTAS", paragraphFont);
            
            // We add an image (Añadimos una imagen)
            Image image;
            try {
                image = Image.getInstance(iTextExampleImage);  
                //image.setAbsolutePosition(0,0);
                image.setAlignment(image.LEFT | image.TEXTWRAP);
                image.scalePercent(img);
                //chapter.add(image);
                document.add(image);
                document.add(parafo);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" +  ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " +  ex);
            }
            Paragraph salto = new Paragraph(" ", paragraphFont);
            Paragraph parafo2 = new Paragraph("Los resultados de las transferencias del archivo  ******  del convenio ***** son los siguientes:", paragraphFont);
            Paragraph parafo3 =(new Paragraph("Registros erróneos en el archivo: ", paragraphFont));
            parafo3.add(new Paragraph(" ", paragraphFont));
            
            document.add(salto);
            document.add(parafo2);
            document.add(salto);
            document.add(parafo3);
            document.add(salto);
            
            // Second page - some elements
            // Segunda página - Algunos elementos
           /* Chapter chapSecond = new Chapter(new Paragraph(new Anchor("Some elements (Añadimos varios elementos)")), 1);
            Paragraph paragraphS = new Paragraph("Do it by Xules (Realizado por Xules)", subcategoryFont);
            
            // Underline a paragraph by iText (subrayando un párrafo por iText)
            Paragraph paragraphE = new Paragraph("This line will be underlined with a dotted line (Está línea será subrayada con una línea de puntos).");
            DottedLineSeparator dottedline = new DottedLineSeparator();
            dottedline.setOffset(-2);
            dottedline.setGap(2f);
            paragraphE.add(dottedline);
            chapSecond.addSection(paragraphE);
            
            Section paragraphMoreS = chapSecond.addSection(paragraphS);
            // List by iText (listas por iText)
            String text = "test 1 2 3 ";
            for (int i = 0; i < 5; i++) {
                text = text + text;
            }
            List list = new List(List.UNORDERED);
            ListItem item = new ListItem(text);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.add(item);
            text = "a b c align ";
            for (int i = 0; i < 5; i++) {
                text = text + text;
            }
            item = new ListItem(text);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.add(item);
            text = "supercalifragilisticexpialidocious ";
            for (int i = 0; i < 3; i++) {
                text = text + text;
            }
            item = new ListItem(text);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.add(item);
            paragraphMoreS.add(list);
            document.add(chapSecond);
            
            // How to use PdfPTable
            // Utilización de PdfPTable
            
            // We use various elements to add title and subtitle
            // Usamos varios elementos para añadir título y subtítulo*/
           /* Anchor anchor = new Anchor("Registros erróneos en el archivo:", paragraphFont);
            //anchor.setName("Table export to PDF (Exportamos la tabla a PDF)");            
            Chapter chapTitle = new Chapter(new Paragraph(anchor), 1);
            Paragraph paragraph = new Paragraph("Do it by Xules (Realizado por Xules)", subcategoryFont);
            Section paragraphMore = chapTitle.addSection(paragraph);
            paragraphMore.add(new Paragraph("This is a simple example (Este es un ejemplo sencillo)"));*/
            Integer numColumns = 1;
            Integer numRows = 2;
            // We create the table (Creamos la tabla).
            PdfPTable table = new PdfPTable(numColumns); 
            // Now we fill the PDF table 
            // Ahora llenamos la tabla del PDF
            PdfPCell columnHeader;
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int column = 0; column < numColumns; column++) {
                columnHeader = new PdfPCell(new Phrase("Error registrado "));
                columnHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                columnHeader.setBackgroundColor(new BaseColor(140, 194, 219));
                table.addCell(columnHeader);
            }
            table.setHeaderRows(1);
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < numColumns; column++) {
                    table.addCell("El valor del campo 1 no es valido  " + row);
                }
            }

            
            
             for (ArchivoProcesado registroArchivo : archivoProcesadoList) {
                 System.out.println("entra ");
                 
                 System.out.println(registroArchivo.getCodigoProceso());
                  System.out.println("sale ");
                 
             }
            

            

            
            
            
            
            
            
            ///fin
            document.close();
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }catch (Exception e){
            e.printStackTrace();
        
        }finally{
         
         document.close();
         }
      
 
    
    
    
    }
    






    

    
    
}
