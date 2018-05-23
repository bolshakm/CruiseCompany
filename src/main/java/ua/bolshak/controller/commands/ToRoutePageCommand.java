package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToRoutePageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ROUTES = params.getProperty("Routes");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Route> routes = RouteService.findAll();
        request.setAttribute(ROUTES, routes);
        new PaginationCommand().addPagination(request, 5, routes.size(), ROUTES);
        return Page.ROUTE.getPage();
    }
}
