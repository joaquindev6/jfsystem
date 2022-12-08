package com.company.app.controllers;

import com.company.app.models.CategoryProduct;
import com.company.app.models.MarkProduct;
import com.company.app.models.Product;
import com.company.app.models.User;
import com.company.app.services.ProductService;
import com.company.app.services.UserService;
import com.company.app.services.impl.ProductServiceImpl;
import com.company.app.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/inicio"})
public class IndexServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = this.userService.findAllUsers();
        List<Product> products = this.productService.findAllProducts();
        List<CategoryProduct> categories = this.productService.findAllCategories();
        List<MarkProduct> marks = this.productService.findAllMarks();

        req.setAttribute("cantUser", (long) users.size());
        req.setAttribute("cantProduct", (long) products.size());
        req.setAttribute("cantCategory", (long) categories.size());
        req.setAttribute("cantMark", (long) marks.size());

        getServletContext().getRequestDispatcher("/index-start.jsp").forward(req, resp);
    }
}
