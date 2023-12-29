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

@WebServlet(urlPatterns = "/edit")
public class EditProductServlet extends HttpServlet {

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
        if (req.getParameter("id") != null) {
            try {
                long id = Long.parseLong(req.getParameter("id"));
                for (Product product : productService.getProducts()) {
                    if (product.getId() == id) {
                        currentProduct = product;
                    }
                }
            } catch (NumberFormatException e) {
                resp.sendRedirect("/");
            }

        }
        if (req.getParameter("delete") != null) {
            if (req.getParameter("delete").equals("true")) {
                productService.deleteProduct(currentProduct.getId());
                resp.sendRedirect("/products/my-products");
                return;
            }
        }
        User seller = userService.getUserById(currentProduct.getIdOfSeller());
        req.setAttribute("seller", seller.getNickname());
        req.getSession().setAttribute("currentProduct", currentProduct);
        req.setAttribute("name", currentProduct.getName());
        req.setAttribute("desc", currentProduct.getDescription());
        req.setAttribute("price", currentProduct.getPrice());
        req.setAttribute("date", currentProduct.getDateOfAdd().toString());
        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null) {
            if (!req.getParameter("name").equals("")) {
                productService.editNameOfProduct(currentProduct.getId(), req.getParameter("name"));
            }
        }
        if (req.getParameter("description") != null) {
            if (!req.getParameter("description").equals("")) {
                productService.editDescriptionOfProduct(currentProduct.getId(), req.getParameter("description"));
            }
        }
        if (req.getParameter("city") != null) {
            if (!req.getParameter("city").equals("")) {
                productService.editCityOfProduct(currentProduct.getId(), req.getParameter("city"));
            }
        }
        if (req.getParameter("price") != null) {
            if (!req.getParameter("price").equals("")) {
                productService.editPriceOfProduct(currentProduct.getId(), req.getParameter("price"));
            }
        }
        resp.sendRedirect("/products/my-products");
    }
}
