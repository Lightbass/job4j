package ru.job4j.crud.filter;

import ru.job4j.crud.model.User;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.*;
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
    private final Validate logic = ValidateService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String action = request.getParameter("action") == null ? "null" : "add";
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if (session.getAttribute("login") == null) {
            if (!(uri.contains("/signin") || uri.contains("/create") || uri.contains("/index.html") || uri.contains("/json")
                    || (uri.equals(request.getContextPath() + "/")) && action.equals("add") && ((HttpServletRequest) servletRequest).getMethod().equals("POST"))) {
                ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
                return;
            }
        } else {
            String login = (String) session.getAttribute("login");
            User user = logic.findByLogin(login);
            if (user == null) {
                ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
                return;
            } else if (request.getRequestURI().contains("/logout")) {
                session.invalidate();
                ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
                return;
            }
            servletRequest.setAttribute("role", user.getRole());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
