package com.company.app.filters;

import com.company.app.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
 * Este filtro verifica si el que ingreso es un administrador o un usurio para darles permisos en las demas paginas menos control
 */

@WebFilter({"/inventario/*", "/inicio", "/usuario/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("userLogin");
        if (user != null) {
            if ("ROLE_ADMIN".equals(user.getUserRole().getNombre()) || "ROLE_USER".equals(user.getUserRole().getNombre())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                resp.sendRedirect(req.getContextPath() + "/inicio");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/sesion");
        }
    }
}
