package com.company.app.controllers.view;

import com.company.app.models.MarkProduct;
import com.company.app.services.ProductService;
import com.company.app.services.impl.ProductServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/inventario/marca-producto")
public class MarkProductServletShow extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MarkProduct> marks = this.productService.findAllMarks();
        req.setAttribute("marks", marks);
        req.getSession().removeAttribute("idMark");

        if (req.getSession().getAttribute("successMark") != null) {
            req.setAttribute("success", req.getSession().getAttribute("successMark"));
            req.getSession().removeAttribute("successMark");
        }

        getServletContext().getRequestDispatcher("/product-mark.jsp").forward(req, resp);
    }
}
