package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRouteCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idRoute = request.getParameter("idRoute");
        Route route = RouteService.findById(Integer.parseInt(idRoute));
        RouteService.delete(route);
        return new ToPortsPage().execute(request, response);
    }
}
