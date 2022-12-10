package com.company.app.controllers;

import com.company.app.models.CategoryProduct;
import com.company.app.services.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/inventario/categoria-producto/formulario/save")
public class FormCategoryProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("name");
        String descripcion = req.getParameter("description");

        Map<String, String> errors = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errors.put("nameError", "El campo nombre de categoría no puede estar vacío.");
        }

        long id;
        try {
            id = (Long) req.getSession().getAttribute("idCategory");
        } catch (Exception ex) {
            id = 0L;
        }

        if (errors.isEmpty()) {
            CategoryProduct categoryProduct = new CategoryProduct();

            if (id > 0) {
                categoryProduct.setId(id);
            }

            categoryProduct.setNombre(nombre.toUpperCase().trim());
            categoryProduct.setDescripcion(descripcion.toUpperCase().trim());

            this.productService.saveCategory(categoryProduct);
            req.getSession().removeAttribute("idCategory");
            req.getSession().setAttribute("successCategory", "Los datos de la categoría han sido guardados.");
            resp.sendRedirect(req.getContextPath() + "/inventario/categoria-producto");
        } else {
            req.getSession().setAttribute("errorsCategory", errors);
            resp.sendRedirect(req.getContextPath() + "/inventario/categoria-producto/formulario");
        }
    }
}
