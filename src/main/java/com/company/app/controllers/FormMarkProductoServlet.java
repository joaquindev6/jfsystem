package com.company.app.controllers;

import com.company.app.models.MarkProduct;
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

@WebServlet("/inventario/marca-producto/formulario/save")
public class FormMarkProductoServlet extends HttpServlet {

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
            id = (Long) req.getSession().getAttribute("idMark");
        } catch (Exception ex) {
            id = 0L;
        }

        if (errors.isEmpty()) {
            MarkProduct markProduct = new MarkProduct();

            if (id > 0) {
                markProduct.setId(id);
            }

            markProduct.setNombre(nombre.toUpperCase().trim());
            markProduct.setDescripcion(descripcion.toUpperCase().trim());

            this.productService.saveMark(markProduct);
            req.getSession().removeAttribute("idMark");
            req.getSession().setAttribute("successMark", "Los datos de la marca han sido guardados.");
            resp.sendRedirect(req.getContextPath() + "/inventario/marca-producto");
        } else {
            req.getSession().setAttribute("errorsMark", errors);
            resp.sendRedirect(req.getContextPath() + "/inventario/marca-producto/formulario");
        }
    }
}
