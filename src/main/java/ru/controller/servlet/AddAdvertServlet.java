package ru.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.util.service.ProductService;
import ru.util.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/add")
public class AddAdvertServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("exit") != null) {
            if (req.getParameter("exit").equals("true")) {
                req.getSession().removeAttribute("currentUser");
                req.getRequestDispatcher("/WEB-INF/jsp/authorisation.jsp").forward(req, resp);
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/add-advert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String city = req.getParameter("city");
        float price = Float.parseFloat(req.getParameter("price"));
        req.getSession().setAttribute("addProductName", name);
        req.getSession().removeAttribute("addProductName");
        req.getSession().removeAttribute("addImageName");
        productService.putProduct(name, desc, price, city, category, (long) req.getSession().getAttribute("currentId"));
        resp.sendRedirect("/products/my-products");
    }
}
