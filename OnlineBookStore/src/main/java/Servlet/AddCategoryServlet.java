package Servlet;

import Domain.Category;
import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/addcategory")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddCategoryServlet extends HttpServlet {
    private AdminService adminService;

    public void init() {
        // Initialize the adminService object
        adminService = new AdminService();
    }
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] parts = contentDisposition.split(";");
        for (String partItem : parts) {
            if (partItem.trim().startsWith("filename")) {
                return partItem.substring(partItem.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    private String generateUniqueFileName(String originalFileName) {
        String fileExtension = getFileExtension(originalFileName);
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        return uniqueFileName;
    }
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hi fom doget");
        request.getRequestDispatcher("admin/Addcategory.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the category name from the form data
        System.out.println("hi fom dopost");
        String categoryName = request.getParameter("name");

        Part imagePart = request.getPart("image");
        String fileName = getFileName(imagePart);
        String uniqueFileName = generateUniqueFileName(fileName);


        String filePath = getServletContext().getRealPath("./assets/images/category/") + uniqueFileName;

// Create an input stream from the uploaded file part
        InputStream inputStream = imagePart.getInputStream();

// Save the uploaded file to the specified file path
        Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        // Create a new Category object
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryImage(uniqueFileName);

        try {
            // Add the category to the database
            boolean addCategorySuccessful = adminService.addCategory(category);
            if (addCategorySuccessful) {
                System.out.println("Category added successfully!");
                // Set a success message in the request attribute
                request.setAttribute("message", "Category added successfully!");
            } else {
                // Set an error message in the request attribute
                request.setAttribute("message", "Failed to add the category.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirect back to the add category page
        response.sendRedirect("AdminHome");
    }
}
