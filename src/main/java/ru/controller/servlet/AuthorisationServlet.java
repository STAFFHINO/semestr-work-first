package ru.controller.servlet;

import ru.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.util.service.UserService;
import ru.util.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/authorisation")
public class AuthorisationServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/authorisation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        if (!login.equals("") && !password.equals("")) {
            User user = userService.getUser(login, password);
            if (user == null) {
                resp.sendRedirect("/registration");
            } else {
                req.getSession().setAttribute("currentId", user.getId());
                req.getSession().setAttribute("currentLogin", user.getLogin());
                req.getSession().setAttribute("currentRole", user.getRole());
                req.getSession().setAttribute("currentNickname", user.getNickname());
                resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/authorisation");
        }
    }
}
