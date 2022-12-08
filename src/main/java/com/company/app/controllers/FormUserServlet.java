package com.company.app.controllers;

import com.company.app.models.DocumentType;
import com.company.app.models.User;
import com.company.app.models.UserRole;
import com.company.app.services.UserService;
import com.company.app.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Este servlet unicamente se encargara de realizar las funciones y luego redireccionar al controlador de vista.
 * Funciones de la tabla de usuarios.
 */

@WebServlet("/control/usuarios/formulario/save") //Esta es solo para redireccionar a este servlet, no se mostrara en la vista
public class FormUserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl(); //Instanciamos el servicio a utilizar

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Obtenemos los datos el formulario
        String nombres = req.getParameter("names");
        String apellidos = req.getParameter("lastNames");
        String email = req.getParameter("email");
        String nroDocu = req.getParameter("nroDocu");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        long idTypeDocu, idRole;
        try {
            idTypeDocu = Long.parseLong(req.getParameter("tipeDocu"));
        } catch (Exception ex) {
            idTypeDocu = 0L;
        }
        try {
            idRole = Long.parseLong(req.getParameter("role"));
        } catch (Exception ex) {
            idRole = 0L;
        }

        //Si hay errores en los datos lo guardamos en un mapeo para mostrar los errores en la vista
        Map<String, String> errors = new HashMap<>();
        if (nombres == null || nombres.isBlank()) {
            errors.put("namesError", "El campo nombres no puede estar vacío.");
        }
        if (apellidos == null || apellidos.isBlank()) {
            errors.put("lastNamesError", "El campo apellidos no puede estar vacío.");
        }
        if (email == null || email.isBlank()) {
            errors.put("emailError", "El campo correo electrónico no puede estar vacío.");
        }
        if (nroDocu == null || nroDocu.isBlank()) {
            errors.put("nroDocuError", "El campo número de documento no puede estar vacío.");
        }
        if (username == null || username.isBlank()) {
            errors.put("usernameError", "El campo nombre de usuario no puede estar vacío.");
        }
        if (password == null || password.isBlank()) {
            errors.put("passError", "El campo contraseña no puede estar vacío.");
        }
        if (idTypeDocu <= 0) {
            errors.put("idTypeDocuError", "Seleccione el tipo de documento.");
        }
        if (idRole <= 0) {
            errors.put("roleError", "Seleccione el tipo de cargo.");
        }

        //Obtenemos el idUser que se guardo en la sesion al darle clic en editar
        long id;
        try {
            id = (Long) req.getSession().getAttribute("idUser");
        } catch (Exception ex) {
            id = 0;
        }

        //Si no hay errores guardados pasara para guardar los datos en la base de datos
        if (errors.isEmpty()) {
            User user = new User();

            //Verifica si se ha seleccionado un registro a editar
            if (id > 0) {
                user.setId(id);
            }

            user.setNombres(nombres.toUpperCase().trim());
            user.setApellidos(apellidos.toUpperCase().trim());
            user.setApellidos(apellidos.toUpperCase().trim());
            user.setEmail(email.toUpperCase().trim());
            user.setNrodocu(nroDocu);
            user.setUsername(username);
            user.setPassword(password);

            DocumentType documentType = new DocumentType();
            documentType.setId(idTypeDocu);
            user.setDocumentType(documentType);

            UserRole role = new UserRole();
            role.setId(idRole);
            user.setUserRole(role);

            //Guarda los datos en la base de datos
            this.userService.saveUser(user);

            //Eliminamos el idUser que se selecciono a editar en el caso que exista
            req.getSession().removeAttribute("idUser");

            //Muestra un mensaje de confirmacion
            req.getSession().setAttribute("successUser", "Los datos del usuario han sido guardados.");

            //Redirecciona al servlet vista de la tabla
            resp.sendRedirect(req.getContextPath() + "/control/usuarios");
        } else {

            //En el caso que haya errores al registrar guardar los errores y los pasar a la vista para mostrarlo
            req.getSession().setAttribute("errorsUser", errors);
            resp.sendRedirect(req.getContextPath() + "/control/usuarios/formulario");
        }
    }
}
