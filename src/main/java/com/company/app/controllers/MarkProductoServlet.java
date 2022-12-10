package com.company.app.controllers;

import com.company.app.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inventario/marca-producto/data-show")
public class MarkProductoServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idMark, idDelete;
        try {
            idMark = Long.parseLong(req.getParameter("id"));
            req.getSession().setAttribute("idMark", idMark);
        } catch (NumberFormatException ex) {
            idMark = 0L;
        }
        try {
            idDelete = Long.parseLong(req.getParameter("idDelete"));
        } catch (NumberFormatException ex) {
            idDelete = 0L;
        }
        if (idDelete > 0) {
            this.productService.deleteMark(idDelete);
            resp.sendRedirect(req.getContextPath() + "/inventario/marca-producto");
        } else {
            resp.sendRedirect(req.getContextPath() + "/inventario/marca-producto/formulario");
        }
    }
}
