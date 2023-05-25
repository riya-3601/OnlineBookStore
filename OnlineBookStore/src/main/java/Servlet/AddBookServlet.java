package Servlet;

import Domain.Books;
import Domain.Category;
import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@WebServlet("/AddBook")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddBookServlet extends HttpServlet {
    //private BookService bookService;

    AdminService adminService;

    public void init() {
        // Initialize the bookService object
       // bookService = new BookService();
        adminService=new AdminService();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the form data
        String title = request.getParameter("title");
    String author = request.getParameter("author");
    String isbn = request.getParameter("isbn");
    double price = Double.parseDouble(request.getParameter("price"));
    String publisher = request.getParameter("publisher");
    int rating = Integer.parseInt(request.getParameter("rating"));
    int categoryId = Integer.parseInt(request.getParameter("category"));


        Part imagePart = request.getPart("image");
        String fileName = getFileName(imagePart);
        String uniqueFileName = generateUniqueFileName(fileName);


        String filePath = getServletContext().getRealPath("./assets/images/books/") + uniqueFileName;

// Create an input stream from the uploaded file part
        InputStream inputStream = imagePart.getInputStream();

// Save the uploaded file to the specified file path
        Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);


        Books book = new Books();
        book.setBookISBN(Integer.parseInt(isbn));
        book.setBookTitle(title);
        book.setBookAuthor(author);
        book.setBookPrice(price);
        book.setBookPublisher(publisher);
        book.setBookRating(rating);
        book.setCategoryId(categoryId);
        book.setBookImage(uniqueFileName);

        // Add the book to the database
        try {
            boolean addbooksuccessful=adminService.addBook(book);
            if (addbooksuccessful) {
                System.out.println("Successful");
                // Set a success message in the request attribute
                request.setAttribute("message", "Book added successfully!");
            } else {
                System.out.println("Unsuccesful");
                // Set an error message in the request attribute
                request.setAttribute("message", "Failed to add the book.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirect to the Admin Home page
        response.sendRedirect("AdminHome");
    }
}

    // Create path components to save the file
//    final String path = "/Users/lakshmikeerthanaaravapalli/eclipse-workspace-jee/CakeStore/WebContent/Images";
//    final Part filePart = request.getPart("Cakeimg");
//    final String fileName = getFileName(filePart);
//
//    OutputStream out = null;
//    InputStream filecontent = null;
//    final PrintWriter writer = response.getWriter();
//	    try {
//                out = new FileOutputStream(new File(path + File.separator
//                + fileName));
//
//                img_path = "/Images" + File.separator + fileName;
//                filecontent = filePart.getInputStream();
//
//                int read = 0;
//final byte[] bytes = new byte[1024];
//
//        while ((read = filecontent.read(bytes)) != -1) {
//        out.write(bytes, 0, read);
//        }
//        writer.println("New file " + fileName + " created at " + path);
//
//
//        } catch (FileNotFoundException fne) {
//        writer.println("You either did not specify a file to upload or are "
//        + "trying to upload a file to a protected or nonexistent "
//        + "location.");