package com.company.app.controllers.view;

import com.company.app.models.User;
import com.company.app.services.UserService;
import com.company.app.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/*
 * Este servlet unicamente se encargara de mostrar los datos en al vista
 */

@WebServlet("/control/usuarios") //Esta es la ruta que se mostrara
public class UserServletShow extends HttpServlet {

    private UserService userService = new UserServiceImpl(); //Instanciamos el servicio a utilizar

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = this.userService.findAllUsers();
        req.setAttribute("users", users);

        //Al volver a la vista de la tabla elimina el idUser de la sesion para evitar que los datos de este usuario se siga mostrando
        req.getSession().removeAttribute("idUser");

        //Mensaje de confirmacion al registrar un nuevo usuario
        if (req.getSession().getAttribute("successUser") != null) {
            req.setAttribute("success", req.getSession().getAttribute("successUser"));
            req.getSession().removeAttribute("successUser");
        }

        getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
