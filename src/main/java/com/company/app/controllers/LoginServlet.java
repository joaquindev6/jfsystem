package com.company.app.controllers;

import com.company.app.models.User;
import com.company.app.services.UserService;
import com.company.app.services.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/sesion")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("invalid") != null) {
            req.setAttribute("invalidLogin", req.getSession().getAttribute("invalid"));
            req.getSession().removeAttribute("invalid");
        }
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

       if (username != null && password != null) {
           Optional<User> user = this.userService.findByUsername(username);
           if (user.isPresent()) {
               if (user.get().getPassword().equals(password)) {
                   req.getSession().setAttribute("userLogin", user.get());
                   resp.sendRedirect(req.getContextPath() + "/inicio");
               } else {
                   resp.sendRedirect(req.getContextPath() + "/sesion");
               }
           } else {
               req.getSession().setAttribute("invalid", "Usuario o contraseña incorrecta.");
               resp.sendRedirect(req.getContextPath() + "/sesion");
           }
       }
    }
}
