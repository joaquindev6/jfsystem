package com.company.app.controllers.view;

import com.company.app.models.CategoryProduct;
import com.company.app.models.MarkProduct;
import com.company.app.models.Product;
import com.company.app.models.UnidadMedida;
import com.company.app.services.ProductService;
import com.company.app.services.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/inventario/productos/formulario")
public class FormProductServletShow extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UnidadMedida> unidMeds = this.productService.findAllUnidMeds();
        req.setAttribute("unidMeds", unidMeds);

        List<MarkProduct> marks = this.productService.findAllMarks();
        req.setAttribute("marks", marks);

        List<CategoryProduct> categories = this.productService.findAllCategories();
        req.setAttribute("categories", categories);

        long id;
        try {
            id = (Long) req.getSession().getAttribute("idProduct");
        } catch (Exception ex) {
            id = 0;
        }

        if (id > 0) {
            Optional<Product> optionalProduct = this.productService.findByIdProduct(id);
            optionalProduct.ifPresent(product -> req.setAttribute("product", product));
        } else {
            if (req.getSession().getAttribute("errorsProduct") != null) {
                req.setAttribute("errorsView", req.getSession().getAttribute("errorsProduct"));
                req.getSession().removeAttribute("errorsProduct");
            }
        }

        getServletContext().getRequestDispatcher("/form-product.jsp").forward(req, resp);
    }
}
