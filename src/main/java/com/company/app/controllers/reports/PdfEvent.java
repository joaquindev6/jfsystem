package com.company.app.controllers.reports;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class PdfEvent implements IEventHandler {

    private final Document documento;

    public PdfEvent(Document doc) {
        documento = doc;
    }

    //Crea el rectangulo donde pondremos el encabezado
    private Rectangle crearRectanguloEncabezado(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xEncabezado = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yEncabezado = pdfDoc.getDefaultPageSize().getTop() - documento.getTopMargin();
        float anchoEncabezado = page.getPageSize().getWidth() - 72;
        float altoEncabezado = 50F;

        Rectangle rectanguloEncabezado = new Rectangle(xEncabezado, yEncabezado, anchoEncabezado, altoEncabezado);

        return rectanguloEncabezado;
    }

    //Crea el rectangulo donde pondremos el pie de pagina
    private Rectangle crearRectanguloPie(PdfDocumentEvent docEvent) {
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();

        float xPie = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
        float yPie = pdfDoc.getDefaultPageSize().getBottom() ;
        float anchoPie = page.getPageSize().getWidth() - 72;
        float altoPie = 50F;

        Rectangle rectanguloPie = new Rectangle(xPie, yPie, anchoPie, altoPie);

        return rectanguloPie;
    }

    //Crea la tabla que contendra el mensaje del encabezado
    private Table crearTablaEncabezado(PdfDocumentEvent docEvent, String mensaje) {
        PdfPage page = docEvent.getPage();

        File file = new File("logo-report.png");
        Image image = null;
        try {
            ImageData data = ImageDataFactory.create(file.getAbsolutePath());
            image = new Image(data);
            image.setMarginRight(20F);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        float[] anchos = {1F, 1F};
        Table tablaEncabezado = new Table(anchos);
        tablaEncabezado.addCell(new Cell().add(image).setBorder(Border.NO_BORDER));
        tablaEncabezado.addCell(new Cell().add(new Paragraph(mensaje)).setBorder(Border.NO_BORDER));
        return tablaEncabezado;
    }

    //Crea la tabla de pie de pagina, con el numero de pagina
    private Table crearTablaPie(PdfDocumentEvent docEvent) {
        PdfPage page = docEvent.getPage();
        float[] anchos = {1F};
        Table tablaPie = new Table(anchos);
        tablaPie.setHorizontalAlignment(HorizontalAlignment.CENTER);
        int pageNum = docEvent.getDocument().getPageNumber(page);

        Cell cell = new Cell();
        cell.add(new Paragraph("- Página " + pageNum + " -"));
        cell.setBorder(Border.NO_BORDER);

        tablaPie.addCell(cell);

        return tablaPie;
    }

    //Agrega los rectangulos y tablas del encabezado y pie de pagina en el documento
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

        Table tablaEncabezado = this.crearTablaEncabezado(docEvent, "SOFTWARE ERP - JC SYSTEM");
        Rectangle rectanguloEncabezado = this.crearRectanguloEncabezado(docEvent);
        Canvas canvasEncabezado = new Canvas(canvas, pdfDoc, rectanguloEncabezado);
        canvasEncabezado.add(tablaEncabezado);

        Table tablaNumeracion = this.crearTablaPie(docEvent);
        Rectangle rectanguloPie = this.crearRectanguloPie(docEvent);
        Canvas canvasPie = new Canvas(canvas, pdfDoc, rectanguloPie);
        canvasPie.add(tablaNumeracion);
    }
}
