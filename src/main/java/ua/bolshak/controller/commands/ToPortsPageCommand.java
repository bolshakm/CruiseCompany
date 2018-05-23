package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToPortsPageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String EXCURSIONS = params.getProperty("Excursions");
    private static final String PORTS = params.getProperty("Ports");
    private static final String ROUTES = params.getProperty("Routes");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Excursion> excursions = ExcursionService.findAll();
        List<Port> ports = PortService.findAll();
        request.setAttribute(EXCURSIONS, excursions);
        request.setAttribute(PORTS, ports);
        request.setAttribute(ROUTES, RouteService.findAll());
        new PaginationCommand().addPagination(request, 5, excursions.size(), EXCURSIONS);
        new PaginationCommand().addPagination(request, 5, ports.size(), PORTS);
        return Page.PORT.getPage();
    }
}
