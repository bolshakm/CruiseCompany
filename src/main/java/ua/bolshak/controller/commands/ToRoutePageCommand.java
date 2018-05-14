package ua.bolshak.controller.commands;

import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRoutePageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ROUTES = params.getProperty("Routes");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(ROUTES, RouteService.findAll());
        return "/jsp/route.jsp";
    }
}
