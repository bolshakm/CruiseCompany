package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRouteCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("idRoute");
        String name = request.getParameter("name");
        String[] selectedPorts = request.getParameterValues("selectedPorts");
        Route route = RouteService.findById(Integer.parseInt(id));
        route.setName(name);
        route.setPorts(PortService.getListPort(selectedPorts));
        RouteService.update(route);
        return new ToRoutePageCommand().execute(request, response);
    }
}
