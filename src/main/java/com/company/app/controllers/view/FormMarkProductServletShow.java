package com.company.app.controllers.view;

import com.company.app.models.MarkProduct;
import com.company.app.services.ProductService;
import com.company.app.services.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/inventario/marca-producto/formulario")
public class FormMarkProductServletShow extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = (Long) req.getSession().getAttribute("idMark");
        } catch (Exception ex) {
            id = 0L;
        }
        if (id > 0) {
            Optional<MarkProduct> optionalMark = this.productService.findByIdMark(id);
            optionalMark.ifPresent(mark -> req.setAttribute("mark", mark));
        } else {
            if (req.getSession().getAttribute("errorsMark") != null) {
                req.setAttribute("errorsView", req.getSession().getAttribute("errorsMark"));
                req.getSession().removeAttribute("errorsMark");
            }
        }
        getServletContext().getRequestDispatcher("/form-product-mark.jsp").forward(req, resp);
    }
}
