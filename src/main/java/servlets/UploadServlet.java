package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


@MultipartConfig // Bắt buộc để servlet xử lý file upload
public class UploadServlet extends HttpServlet {

    // Nơi lưu file trên server (tùy bạn cấu hình)
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy đường dẫn thực tế đến thư mục "uploads" trong project
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir(); // tạo folder nếu chưa có

        // Lặp qua tất cả các phần (form fields + file)
        for (Part part : req.getParts()) {
            if (part.getName().equals("file") && part.getSize() > 0) {
                // Lấy tên file gốc
                String fileName = part.getSubmittedFileName();
                String filePath = uploadPath + File.separator + fileName;

                // Ghi file ra thư mục server
                try (InputStream input = part.getInputStream();
                     FileOutputStream output = new FileOutputStream(filePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h3>Upload thành công!</h3>");
        resp.getWriter().println("<a href='upload.jsp'>Quay lại</a>");
    }
}
