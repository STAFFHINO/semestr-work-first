package ru.controller.filter;

import ru.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "userFilter")
public class UserFilter implements Filter {
    private HttpServletRequest req;
    private final String[] noAccessForUserUrls = {"/basket", "/add", "/product", "/products/my-product", "/edit"};
    private final String[] noAccessForAuthorisedUserUrls = {"/add", "/products/my-product", "/edit"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        if (session == null) {
            filterChain.doFilter(req, resp);
        } else {
            User user = (User) session.getAttribute("currentUser");
            if (user == null) {
                if (isAccessedForUser()) {
                    filterChain.doFilter(req, resp);
                } else {
                    resp.sendRedirect("/authorisation");
                }
            } else {
                String role = user.getRole();
                switch (role) {
                    case "seller":
                        filterChain.doFilter(req, resp);
                        break;
                    case "authorised":
                        if (isAccessedForAuthorised()) {
                            filterChain.doFilter(req, resp);
                        } else {
                            resp.sendRedirect("/authorisation");
                        }
                        break;
                    case "public":
                        if (isAccessedForUser()) {
                            filterChain.doFilter(req, resp);
                        } else {
                            resp.sendRedirect("/authorisation");
                        }
                        break;
                }
            }
        }
    }

    private boolean isAccessedForAuthorised() {
        String requestURL = req.getRequestURL().toString();

        for (String URL : noAccessForAuthorisedUserUrls) {
            if (requestURL.contains(URL)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAccessedForUser() {
        String requestURL = req.getRequestURL().toString();

        for (String URL : noAccessForUserUrls) {
            if (requestURL.contains(URL)) {
                return false;
            }
        }
        return true;
    }
}
