package com.company.app.controllers;

import com.company.app.models.CategoryProduct;
import com.company.app.models.MarkProduct;
import com.company.app.models.Product;
import com.company.app.models.UnidadMedida;
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

@WebServlet("/inventario/productos/formulario/save")
public class FormProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("name");
        String fechaReg = req.getParameter("dateReg");
        long idUnidMed, idMark, idCategory;
        try {
            idUnidMed = Long.parseLong(req.getParameter("uniMed"));
        } catch (NumberFormatException ex) {
            idUnidMed = 0L;
        }
        try {
            idMark = Long.parseLong(req.getParameter("mark"));
        } catch (NumberFormatException ex) {
            idMark = 0L;
        }
        try {
            idCategory = Long.parseLong(req.getParameter("category"));
        } catch (NumberFormatException ex) {
            idCategory = 0L;
        }
        int cantidad;
        try {
            cantidad = Integer.parseInt(req.getParameter("amount"));
        } catch (NumberFormatException ex) {
            cantidad = 0;
        }
        double precio;
        try {
            precio = Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            precio = 0D;
        }

        Map<String, String> errors = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errors.put("nameError", "El campo nombre del producto no puede estar vacío.");
        }
        if (fechaReg == null || fechaReg.isBlank()) {
            errors.put("dateRegError", "Ingrese la fecha de registro.");
        }
        if (idUnidMed <= 0) {
            errors.put("idUnidMedError", "Seleccione la unidad de medida.");
        }
        if (idMark <= 0) {
            errors.put("idMarkError", "Seleccione la marca.");
        }
        if (idCategory <= 0) {
            errors.put("idCategoryError", "Seleccione la categoría.");
        }
        if (cantidad <= 0) {
            errors.put("amountError", "Ingrese la cantidad.");
        }
        if (precio <= 0) {
            errors.put("priceError", "Ingrese el precio.");
        }

        long id;
        try {
            id = (Long) req.getSession().getAttribute("idProduct");
        } catch (Exception ex) {
            id = 0;
        }

        if (errors.isEmpty()) {
            Product product = new Product();

            if (id > 0) {
                product.setId(id);
            }

            product.setNombre(nombre.toUpperCase().trim());
            product.setStock(cantidad);
            product.setPrecio(precio);
            product.setFecharegi(fechaReg);

            UnidadMedida unidadMedida = new UnidadMedida();
            unidadMedida.setId(idUnidMed);
            product.setUnidMed(unidadMedida);

            MarkProduct mark = new MarkProduct();
            mark.setId(idMark);
            product.setMarkProduct(mark);

            CategoryProduct category = new CategoryProduct();
            category.setId(idCategory);
            product.setCategoryProduct(category);

            this.productService.saveProduct(product);
            req.getSession().removeAttribute("idProduct");
            req.getSession().setAttribute("successProduct", "Los datos del producto han sido guardados.");
            resp.sendRedirect(req.getContextPath() + "/inventario/productos");
        } else {
            req.getSession().setAttribute("errorsProduct", errors);
            resp.sendRedirect(req.getContextPath() + "/inventario/productos/formulario");
        }
    }
}
