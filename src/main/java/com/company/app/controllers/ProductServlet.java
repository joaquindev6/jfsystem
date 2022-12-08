package com.company.app.controllers;

import com.company.app.services.ProductService;
import com.company.app.services.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inventario/productos/data-show")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idProduct, idDelete;
        try {
            idProduct = Long.parseLong(req.getParameter("id"));
            req.getSession().setAttribute("idProduct", idProduct);
        } catch (NumberFormatException ex) {
            idProduct = 0L;
        }
        try {
            idDelete = Long.parseLong(req.getParameter("idDelete"));
        } catch (NumberFormatException ex) {
            idDelete = 0L;
        }
        if (idDelete > 0) {
            this.productService.deleteProduct(idDelete);
            resp.sendRedirect(req.getContextPath() + "/inventario/productos");
        } else {
            resp.sendRedirect(req.getContextPath() + "/inventario/productos/formulario");
        }
    }
}
