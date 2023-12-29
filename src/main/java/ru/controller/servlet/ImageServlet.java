package ru.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.util.service.ProductService;
import ru.util.service.impl.ProductServiceImpl;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@MultipartConfig
@WebServlet(urlPatterns = {"/images/*"})
public class ImageServlet extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getPathInfo().substring(1);
        String filename = productService.loadImage(productName);
        File file = new File("..\\fileForImages\\Image\\" + filename);
        resp.setHeader("Content-Type", filename);
        resp.setHeader("Content-Length", String.valueOf(file.length()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        Files.copy(file.toPath(), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        req.getSession().setAttribute("addImageName", fileName);
        InputStream inputStream = filePart.getInputStream();
        System.out.println("Single Filename:: " + fileName);
        writeFile("..\\fileForImages\\Image\\" + fileName, inputStream);
        req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
    }

    public void downloadDefaultAvatar() {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new URL("https://w7.pngwing.com/pngs/562/56/png-transparent-playstation-drawing-game-controllers-line-art-video-game-gamepad-game-angle-white.png").openStream());
            new File("..\\fileForImages\\Image").mkdirs();
            File file = new File("..\\fileForImages\\Image\\def.png");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(String path, InputStream input) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = input.read(bytes)) != -1) fileOutputStream.write(bytes, 0, read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

