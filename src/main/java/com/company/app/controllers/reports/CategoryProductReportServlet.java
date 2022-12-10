package com.company.app.controllers.reports;

import com.company.app.models.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/inventario/categoria-producto/reporte-de-categorias-de-productos.pdf")
public class CategoryProductReportServlet extends HttpServlet {

    @Inject
    private PdfReport pdfReport;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userLogin");
        resp.setContentType("application/pdf");
        OutputStream out = resp.getOutputStream();
        pdfReport.initReportPDF(out, 3, user);
    }
}
