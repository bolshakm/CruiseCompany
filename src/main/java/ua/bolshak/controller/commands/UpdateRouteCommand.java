package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRouteCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_ROUTE = params.getProperty("idRoute");
    private static final String NAME = params.getProperty("name");
    private static final String SELECTED_PORTS = params.getProperty("selectedPorts");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter(ID_ROUTE);
        String name = request.getParameter(NAME);
        String[] selectedPorts = request.getParameterValues(SELECTED_PORTS);
        Route route = RouteService.findById(Integer.parseInt(id));
        route.setName(name);
        route.setPorts(PortService.getListPort(selectedPorts));
        RouteService.update(route);
        return new ToRoutePageCommand().execute(request, response);
    }
}
