package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateRouteCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("idRoute");
        Route route = RouteService.findById(Integer.parseInt(id));
        request.setAttribute("idRoute", route.getId());
        request.setAttribute("name", route.getName());
        request.setAttribute("selectedPorts", route.getPorts());
        return new ToRouteCardCommand().execute(request, response);
    }
}
