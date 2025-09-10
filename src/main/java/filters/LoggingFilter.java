package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        long start = System.currentTimeMillis();

        System.out.println(">>> Request: " + request.getMethod() + " " + request.getRequestURI());

        chain.doFilter(req, res);

        long end = System.currentTimeMillis();
        System.out.println("<<< Response for " + request.getRequestURI() + " in " + (end - start) + " ms");
    }
}
