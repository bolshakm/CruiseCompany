package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRouteCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_ROUTE = params.getProperty("idRoute");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Route route = RouteService.findById(Integer.parseInt(request.getParameter(ID_ROUTE)));
        RouteService.delete(route);
        return new ToRoutePageCommand().execute(request, response);
    }
}
