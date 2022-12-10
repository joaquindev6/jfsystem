package com.company.app.controllers;

import com.company.app.services.UserService;
import com.company.app.services.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
 * Este servlet unicamente se encargara de realizar las funciones y luego redireccionar al controlador de vista.
 * Funciones de la tabla de usuarios.
 */

@WebServlet("/control/usuarios/data-show") //Esta es solo para redireccionar a este servlet, no se mostrara en la vista
public class UserServlet extends HttpServlet {

//    private UserService userService = new UserServiceImpl(); //Instanciamos el servicio a utilizar
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Recuperamos los id que se pasa por direccion desde los botones de editar y eliminar
        long id, idDelete;
        try {
            id = Long.parseLong(req.getParameter("id"));
            req.getSession().setAttribute("idUser", id); //Guarda el idUser en la sesion para utilizar y mostrar los datos del usuario por id
        } catch (Exception ex) {
            id = 0;
        }
        try {
            idDelete = Long.parseLong(req.getParameter("idDelete"));
        } catch (Exception ex) {
            idDelete = 0;
        }

        //Si se presiona el boton eliminar guardar el id del usuario y pasara por la condicion
        if (idDelete > 0) {
            this.userService.deleteUser(idDelete); //Elimina el usuario
            resp.sendRedirect(req.getContextPath() + "/control/usuarios"); //Redirecciona al servlet vista de la tabla
        } else {
            resp.sendRedirect(req.getContextPath() + "/control/usuarios/formulario"); //Redirecciona al servlet vista del formulario
        }
    }
}
