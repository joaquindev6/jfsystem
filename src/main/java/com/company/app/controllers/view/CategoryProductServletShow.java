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
import java.util.List;

@WebServlet("/inventario/categoria-producto")
public class CategoryProductServletShow extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryProduct> categories = this.productService.findAllCategories();
        req.setAttribute("categories", categories);
        req.getSession().removeAttribute("idCategory");

        if (req.getSession().getAttribute("successCategory") != null) {
            req.setAttribute("success", req.getSession().getAttribute("successCategory"));
            req.getSession().removeAttribute("successCategory");
        }

        getServletContext().getRequestDispatcher("/product-categories.jsp").forward(req, resp);
    }
}
