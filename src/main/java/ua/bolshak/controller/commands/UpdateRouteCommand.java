package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Route;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateRouteCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String WRONG_INPUT = text.getProperty("wrong.input");
    private static final String ROUTE_NAME_REGEX = regExResources.getProperty("route.name.regexp");
    private static final String ROUTE = params.getProperty("Route");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ID_ROUTE = params.getProperty("idRoute");
    private static final String NAME = params.getProperty("name");
    private static final String SELECTED_PORTS = params.getProperty("selectedPorts");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Pattern namePattern = Pattern.compile(ROUTE_NAME_REGEX);
        String id = request.getParameter(ID_ROUTE);
        String name = request.getParameter(NAME);
        String[] portsId = request.getParameterValues(SELECTED_PORTS);
        Route route = RouteService.findById(Integer.parseInt(id));
        boolean wrongInput = false;
        if (namePattern.matcher(name).matches()) {
            route.setName(name);
        } else {
            route.setName(null);
            wrongInput = true;
        }
        if (portsId != null) {
            route.setPorts(PortService.getListPort(portsId));
        } else {
            wrongInput = true;
        }
        if (!wrongInput){
            RouteService.update(route);
        } else {
            request.setAttribute(ROUTE, route);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            return new ToRouteCardCommand().execute(request, response);
        }
        return new ToRoutePageCommand().execute(request, response);
    }
}
