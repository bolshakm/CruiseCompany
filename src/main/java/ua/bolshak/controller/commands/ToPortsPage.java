package ua.bolshak.controller.commands;

import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToPortsPage implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String EXCURSIONS = params.getProperty("Excursions");
    private static final String PORTS = params.getProperty("Ports");
    private static final String ROUTES = params.getProperty("Routes");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(EXCURSIONS, ExcursionService.findAll());
        request.setAttribute(PORTS, PortService.findAll());
        request.setAttribute(ROUTES, RouteService.findAll());
        return Page.PORT.getPage();
    }
}
