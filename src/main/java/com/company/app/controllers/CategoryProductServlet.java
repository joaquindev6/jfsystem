package com.company.app.controllers;

import com.company.app.services.ProductService;
import com.company.app.services.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inventario/categoria-producto/data-show")
public class CategoryProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idCategory, idDelete;
        try {
            idCategory = Long.parseLong(req.getParameter("id"));
            req.getSession().setAttribute("idCategory", idCategory);
        } catch (NumberFormatException ex) {
            idCategory = 0L;
        }
        try {
            idDelete = Long.parseLong(req.getParameter("idDelete"));
        } catch (NumberFormatException ex) {
            idDelete = 0L;
        }
        if (idDelete > 0) {
            this.productService.deleteCategory(idDelete);
            resp.sendRedirect(req.getContextPath() + "/inventario/categoria-producto");
        } else {
            resp.sendRedirect(req.getContextPath() + "/inventario/categoria-producto/formulario");
        }
    }
}
