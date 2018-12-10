package ua.bolshak.filter;

import ua.bolshak.model.entity.User;
import ua.bolshak.properties.RequestParams;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/CruiseCompany"})
public class CommandSecurityFilter implements Filter {
    private static RequestParams params = RequestParams.getInstance();
        private static final String COMMAND = params.getProperty("command");
    private static final String USER = params.getProperty("user");
    private static final String NULL = "null";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute(USER);
        String command = request.getParameter(COMMAND);
        int key = 0;
        if (user == null) {
            key = 5;
        } else {
            if (user.getRole().getId() == 1) {
                key = 1;
            }
            if (user.getRole().getId() == 2) {
                key = 2;
            }
            if (user.getRole().getId() == 3) {
                key = 3;
            }
            if (user.getRole().getId() >= 4) {
                key = 3;
            }
        }
        command = command == null ? NULL : command;
        List<String> availableCommand = PagesMapping.PAGES_MAP.get(key);
        if (!availableCommand.contains(command)){
            request.getRequestDispatcher("CruiseCompany?command=logout").forward(request,response);
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
