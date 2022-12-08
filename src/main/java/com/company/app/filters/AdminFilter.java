package com.company.app.filters;

import com.company.app.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
 * Este filtro verifica si el que ingreso es un administrador para dar permisos de ingreso la pagina /control/*
 */

@WebFilter({"/control/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //Verifica si existe el susuario y si es administrador
        User user = (User) req.getSession().getAttribute("userLogin");
        if (user != null) {
            if ("ROLE_ADMIN".equals(user.getUserRole().getNombre())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                resp.sendRedirect(req.getContextPath() + "/inicio");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/sesion");
        }
    }
}
