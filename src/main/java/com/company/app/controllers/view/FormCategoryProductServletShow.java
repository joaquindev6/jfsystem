package com.company.app.controllers.view;

import com.company.app.models.CategoryProduct;
import com.company.app.services.ProductService;
import com.company.app.services.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/inventario/categoria-producto/formulario")
public class FormCategoryProductServletShow extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = (Long) req.getSession().getAttribute("idCategory");
        } catch (Exception ex) {
            id = 0L;
        }
        if (id > 0) {
            Optional<CategoryProduct> optionalCategory = this.productService.findByIdCategory(id);
            optionalCategory.ifPresent(category -> req.setAttribute("category", category));
        } else {
            if (req.getSession().getAttribute("errorsCategory") != null) {
                req.setAttribute("errorsView", req.getSession().getAttribute("errorsCategory"));
                req.getSession().removeAttribute("errorsCategory");
            }
        }
        getServletContext().getRequestDispatcher("/form-product-category.jsp").forward(req, resp);
    }
}
