package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MimeTypeFilter implements Filter {
    private String defaultCharset = "UTF-8";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding(defaultCharset);

        chain.doFilter(req, res);

        HttpServletResponse response = (HttpServletResponse) res;
        if (response.getContentType() == null) {
            response.setContentType("text/html; charset=" + defaultCharset);
        }
    }
}

