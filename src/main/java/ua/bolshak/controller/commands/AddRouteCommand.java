package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRouteCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String [] portsId = request.getParameterValues("selectedPorts");
        Route route = new Route();
        route.setName(name);
        route.setPorts(PortService.getListPort(portsId));
        RouteService.add(route);
        return new ToPortsPage().execute(request, response);
    }
}
