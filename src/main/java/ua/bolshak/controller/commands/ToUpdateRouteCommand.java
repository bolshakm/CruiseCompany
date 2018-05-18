package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateRouteCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_ROUTE = params.getProperty("idRoute");
    private static final String ROUTE = params.getProperty("Route");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter(ID_ROUTE);
        Route route = RouteService.findById(Integer.parseInt(id));
        request.setAttribute(ROUTE, route);
        return new ToRouteCardCommand().execute(request, response);
    }
}
