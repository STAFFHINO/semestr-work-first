package ru.util.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import ru.util.service.ProductService;
import ru.util.service.impl.ProductServiceImpl;

@WebListener
public class AddProductListener implements HttpSessionAttributeListener {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getSession().getAttribute("addProductName") != null &&
                event.getSession().getAttribute("addImageName") != null
        ) {
            productService.uploadImage((String) event.getSession().getAttribute("addProductName"),
                    (String) event.getSession().getAttribute("addImageName"));
        }
    }
}
