package ua.bolshak.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"})
public class PageSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/jsp/error.jsp");
        requestDispatcher.forward(request, response);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
