package ru.controller.servlet;

import ru.entity.Product;
import ru.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.util.service.ProductService;
import ru.util.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/basket")
public class BasketServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("exit") != null) {
            if (req.getParameter("exit").equals("true")) {
                req.getSession().removeAttribute("currentUser");
                req.getRequestDispatcher("/WEB-INF/jsp/authorisation.jsp").forward(req, resp);
            }
        }
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        List<Product> listOfProduct = productService.getBasketOfUser(currentUser.getId());
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user != null) {
            if (user.getRole().equals("seller")) {
                req.setAttribute("seller", true);
            }
        }
        if (req.getParameter("delete") != null) {
            if (req.getParameter("delete").equals("true")) {
                productService.deleteBasket(currentUser.getId());
                resp.sendRedirect("/");
                return;
            }
        }
        req.setAttribute("products", listOfProduct);
        req.getRequestDispatcher("/WEB-INF/jsp/basket.jsp").forward(req, resp);
    }
}
