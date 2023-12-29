package ru.controller.servlet;

import ru.entity.Product;
import ru.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.util.service.ProductService;
import ru.util.service.UserService;
import ru.util.service.impl.ProductServiceImpl;
import ru.util.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    Product currentProduct;
    private final ProductService productService = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("exit") != null) {
            if (req.getParameter("exit").equals("true")) {
                req.getSession().removeAttribute("currentUser");
                req.getRequestDispatcher("/WEB-INF/jsp/authorisation.jsp").forward(req, resp);
            }
        }
        if (req.getParameter("add") != null) {
            productService.addProductToBasket((User) req.getSession().getAttribute("currentUser"), req.getParameter("add"));
        }


        if (req.getParameter("name") != null) {
            for (Product product : productService.getProducts()) {
                if (product.getName().equals(req.getParameter("name"))) {
                    currentProduct = product;
                }
            }
        }
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user != null) {
            if (user.getRole().equals("seller")) {
                req.setAttribute("seller", true);
            }
        }
        req.getSession().setAttribute("currentProduct", currentProduct);
        User seller = userService.getUserById(currentProduct.getIdOfSeller());
        req.setAttribute("seller", seller.getNickname());

        req.setAttribute("name", currentProduct.getName());
        req.setAttribute("date", currentProduct.getDateOfAdd().toString());
        req.setAttribute("desc", currentProduct.getDescription());
        req.setAttribute("price", currentProduct.getPrice());

        req.getRequestDispatcher("/WEB-INF/jsp/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
