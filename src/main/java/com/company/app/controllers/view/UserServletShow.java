package com.company.app.controllers.view;

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
import java.util.List;

@WebServlet("/control/usuarios")
public class UserServletShow extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = this.userService.findAllUsers();
        req.setAttribute("users", users);

        req.getSession().removeAttribute("idUser");

        if (req.getSession().getAttribute("successUser") != null) {
            req.setAttribute("success", req.getSession().getAttribute("successUser"));
            req.getSession().removeAttribute("successUser");
        }

        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
