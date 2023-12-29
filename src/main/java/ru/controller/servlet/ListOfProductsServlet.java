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
import java.util.List;

@WebServlet(urlPatterns = "")
public class ListOfProductsServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) == null) {
            new ImageServlet().downloadDefaultAvatar();
        }
        if (req.getParameter("exit") != null) {
            if (req.getParameter("exit").equals("true")) {
                req.getSession().removeAttribute("currentUser");
            }
        }
        System.out.println(req.getPathInfo());
        List<Product> listOfProduct = productService.getProducts();
        if (req.getParameter("seller") != null) {
            if (req.getParameter("seller").equals("yes")) {
                User user = (User) req.getSession().getAttribute("currentUser");
                if (user == null) {
                    req.getRequestDispatcher("/WEB-INF/jsp/authorisation.jsp").forward(req, resp);
                } else {
                    if (user.getRole().equals("authorised")) {
                        user.setRole("seller");
                        req.getSession().setAttribute("currentRole", user.getRole());
                        userService.setSeller(user);
                    }
                }
            }
        }
        if (req.getParameter("sort") != null) {
            String sort = req.getParameter("sort");
            listOfProduct = switch (sort) {
                case "cheap" -> productService.getProductsByPrice();
                case "expensive" -> productService.getProductsByPriceDown();
                case "date" -> productService.getProductsByDate();
                default -> productService.getProducts();
            };
        }

        if (req.getParameter("category") != null) {
            if (req.getParameter("category").equals("")) {
                listOfProduct = productService.getProducts();
            } else {
                listOfProduct = productService.getProductsWithCategory(req.getParameter("category"));
            }
        }
        System.out.println(req.getSession().getAttribute("currentUser"));
        req.setAttribute("products", listOfProduct);
        System.out.println(1111111);
        req.getRequestDispatcher("/WEB-INF/jsp/products.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }
}
