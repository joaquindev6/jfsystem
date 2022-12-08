package com.company.app.controllers.reports;

import com.company.app.models.CategoryProduct;
import com.company.app.models.MarkProduct;
import com.company.app.models.Product;
import com.company.app.models.User;
import com.company.app.services.ProductService;
import com.company.app.services.UserService;
import com.company.app.services.impl.ProductServiceImpl;
import com.company.app.services.impl.UserServiceImpl;
import com.company.app.util.TextFormatString;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfReport {

    private static UserService userService = new UserServiceImpl();
    private static ProductService productService = new ProductServiceImpl();

    public static void initReportPDF(OutputStream out, int option, User user) {
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        Document doc = new Document(pdfDoc, PageSize.A4.rotate());

        PdfEvent evento = new PdfEvent(doc);
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, evento);

        doc.setMargins(75, 36, 75, 36);

        //Segun la opcion que se elija mostrara el reporte especifico
        try {
            switch (option) {
                case 1:
                    doc.add(titleList("Reporte de Usuarios"));
                    doc.add(dataReporte(user));
                    doc.add(tableUsers());
                    break;
                case 2:
                    doc.add(titleList("Reporte de Productos"));
                    doc.add(dataReporte(user));
                    doc.add(tableProducts());
                    break;
                case 3:
                    doc.add(titleList("Reporte de Categorías de Productos"));
                    doc.add(dataReporte(user));
                    doc.add(tableCategory());
                    break;
                case 4:
                    doc.add(titleList("Reporte de Marcas de Productos"));
                    doc.add(dataReporte(user));
                    doc.add(tableMark());
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.close();
    }

    //Permite cambiar el titulo de los reportes
    private static Table titleList(String title) {
        float[] columnWidth = {1F};
        Table table = new Table(columnWidth);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        Cell cell = new Cell();
        cell.setBorder(Border.NO_BORDER);

        Paragraph paragraph = new Paragraph(title);
        paragraph.setMarginBottom(5F);
        paragraph.setFontSize(20F);
        cell.add(paragraph);
        table.addCell(cell);

        return table;
    }

    //Inserta el nombre de usuario y fecha de generacion del archivo PDF
    private static Table dataReporte(User user) {
        float[] columnWidth = {1F};
        Table table = new Table(columnWidth);
        table.setMarginBottom(12F);

        Cell cell = new Cell();
        cell.setBorder(Border.NO_BORDER);

        Paragraph dataUser = new Paragraph();
        dataUser.add("Usuario del sistema: " + TextFormatString.formatTextData(user.getNombres()) + " " + TextFormatString.formatTextData(user.getApellidos()));
        dataUser.setFontSize(12F);
        cell.add(dataUser);
        table.addCell(cell);

        Paragraph dateList = new Paragraph();
        dateList.add("Fecha y hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        dateList.setFontSize(12F);
        cell.add(dateList);
        table.addCell(cell);

        return table;
    }

    //Genera la tabla con los registros de usuarios
    private static Table tableUsers() throws IOException {
        float[] columnWidth = {20F, 20F, 20F, 20F, 20F, 20F, 20F, 20F};
        Table table = new Table(columnWidth);

        String[] tableHeader = {"ID", "NOMBRES", "APELLIDOS", "CORREO ELECTRONICO", "TIPO DE DOCUMENTO",
                "NUMERO DE DOCUMENTO", "USERNAME", "ROL"};
        for (String header : tableHeader) {
            Paragraph paragraph = new Paragraph(header);
            paragraph.setBold();
            paragraph.setFontSize(11F);
            paragraph.setTextAlignment(TextAlignment.CENTER);
            table.addCell(new Cell().add(paragraph));
        }

        List<User> listUsers = userService.findAllUsers();
        listUsers.forEach(u -> {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(u.getId())).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(u.getNombres()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(u.getApellidos()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(u.getEmail()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(u.getDocumentType().getNombre()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(u.getNrodocu()).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(u.getUsername()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(u.getUserRole().getNombre()).setFontSize(11F)));
        });

        return table;
    }

    //Genera la tabla con los registros de productos
    private static Table tableProducts() throws IOException {
        float[] columnWidth = {20F, 20F, 20F, 20F, 20F, 20F, 20F, 20F};
        Table table = new Table(columnWidth);

        String[] tableHeader = {"ID", "NOMBRES", "MARCA", "CATEGORIA",  "UNIDAD DE MEDIDA",
                "STOCK", "PRECIO", "FECHA DE REGISTRO"};
        for (String header : tableHeader) {
            Paragraph paragraph = new Paragraph(header);
            paragraph.setBold();
            paragraph.setFontSize(11F);
            paragraph.setTextAlignment(TextAlignment.CENTER);
            table.addCell(new Cell().add(paragraph));
        }

        List<Product> products = productService.findAllProducts();
        products.forEach(p -> {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(p.getId())).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(p.getNombre()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(p.getMarkProduct().getNombre()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(p.getCategoryProduct().getNombre()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(p.getUnidMed().getNombre()).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(p.getStock())).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(p.getPrecio())).setFontSize(11F).setTextAlignment(TextAlignment.RIGHT)));
            table.addCell(new Cell().add(new Paragraph(p.getFecharegi()).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
        });

        return table;
    }

    //Genera la tabla con los registros de categorias de productos
    private static Table tableCategory() throws IOException {
        float[] columnWidth = {20F, 20F, 20F};
        Table table = new Table(columnWidth);

        String[] tableHeader = {"ID", "NOMBRE", "DESCRIPCION"};
        for (String header : tableHeader) {
            Paragraph paragraph = new Paragraph(header);
            paragraph.setBold();
            paragraph.setFontSize(11F);
            paragraph.setTextAlignment(TextAlignment.CENTER);
            table.addCell(new Cell().add(paragraph));
        }

        List<CategoryProduct> categories = productService.findAllCategories();
        categories.forEach(c -> {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(c.getId())).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(c.getNombre()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(c.getDescripcion()).setFontSize(11F)));
        });

        return table;
    }

    //Genera la tabla con los registros de marcas de productos
    private static Table tableMark() throws IOException {
        float[] columnWidth = {20F, 20F, 20F};
        Table table = new Table(columnWidth);

        String[] tableHeader = {"ID", "NOMBRE", "DESCRIPCION"};
        for (String header : tableHeader) {
            Paragraph paragraph = new Paragraph(header);
            paragraph.setBold();
            paragraph.setFontSize(11F);
            paragraph.setTextAlignment(TextAlignment.CENTER);
            table.addCell(new Cell().add(paragraph));
        }

        List<MarkProduct> marks = productService.findAllMarks();
        marks.forEach(m -> {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(m.getId())).setFontSize(11F).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(m.getNombre()).setFontSize(11F)));
            table.addCell(new Cell().add(new Paragraph(m.getDescripcion()).setFontSize(11F)));
        });

        return table;
    }
}
