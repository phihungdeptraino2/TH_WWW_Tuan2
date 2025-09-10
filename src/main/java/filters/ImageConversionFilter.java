package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageConversionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        HttpServletResponseWrapper wrapped = new HttpServletResponseWrapper(response) {
            private PrintWriter writer = new PrintWriter(buffer, true);
            @Override
            public PrintWriter getWriter() {
                return writer;
            }
        };

        chain.doFilter(req, wrapped);

        byte[] raw = buffer.toByteArray();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(raw)) {
            BufferedImage img = ImageIO.read(bais);
            if (img != null) {
                response.setContentType("image/jpeg");
                ImageIO.write(img, "jpg", response.getOutputStream());
                return;
            }
        }
        response.getOutputStream().write(raw);
    }
}