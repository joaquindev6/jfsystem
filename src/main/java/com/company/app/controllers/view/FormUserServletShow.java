package com.company.app.controllers.view;

import com.company.app.models.DocumentType;
import com.company.app.models.User;
import com.company.app.models.UserRole;
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
import java.util.Optional;

/*
 * Este servlet unicamente se encargara de mostrar los datos en al vista
 */

@WebServlet("/control/usuarios/formulario") //Esta es la ruta que se mostrara
public class FormUserServletShow extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Recuperamos los datos a traves del servicio para mostrarlo en la vista
        List<DocumentType> documents = this.userService.findAllTypeDocu();
        req.setAttribute("documents", documents);
        List<UserRole> roles = this.userService.findAllRoles();
        req.setAttribute("roles", roles);

        //Al dar clic en editar guarda el idUser en la sesion para utilizarlo en las demas clases
        long idUser;
        try {
            idUser = (Long) req.getSession().getAttribute("idUser");
        } catch (Exception ex) {
            idUser = 0L;
        }

        if (idUser > 0) {
            //Muestra los datos en el formulacio al hacer clic en editar
            Optional<User> optionalUser = this.userService.findByIdUser(idUser);
            optionalUser.ifPresent(user -> req.setAttribute("user", user));
        } else {
            //Valida y muestra los errores al ingresar incorrectamente los datos en el formulario
            if (req.getSession().getAttribute("errorsUser") != null) {
                req.setAttribute("errorsView", req.getSession().getAttribute("errorsUser"));
                req.getSession().removeAttribute("errorsUser");
            }
        }

        //Hace una union con los datos y la ruta del servlet y lo muestra en el navegador
        getServletContext().getRequestDispatcher("/form-user.jsp").forward(req, resp);
    }
}
