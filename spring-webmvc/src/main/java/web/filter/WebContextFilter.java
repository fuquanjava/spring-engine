package web.filter;

import web.WebContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/1/11 11:30
 * description:
 * 1.0.0
 */
public class WebContextFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest heq = (HttpServletRequest) request;
            WebContext.registry(heq, (HttpServletResponse) response);
            System.err.println("WebContextFilter .... doFilter..");
            chain.doFilter(request, response);
        } finally {
            WebContext.release();

        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}
