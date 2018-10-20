package ru.job4j.crud;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс - фильтр аунтентификации.
 * @author Alexey Makarov
 * @since 20.10.2018
 * @version 0.1
 */
public class AuthFilter implements Filter {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String action = request.getParameter("action") == null ? "null" : "add";
        if (!(request.getRequestURI().contains("/signin")
                || request.getRequestURI().contains("/create")
                || request.getRequestURI().equals(request.getContextPath() + "/") && action.equals("add"))) {
            HttpSession session = request.getSession();
            synchronized (session) {
                String login = (String) session.getAttribute("login");
                if (logic.findByLogin(login) == null) {
                    ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
                    return;
                } else if (request.getRequestURI().contains("/logout")) {
                    session.invalidate();
                    ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
