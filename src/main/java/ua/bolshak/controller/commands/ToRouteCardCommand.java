package ua.bolshak.controller.commands;

import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRouteCardCommand implements ICommand {private static RequestParams params = RequestParams.getInstance();
    private static final String PORTS = params.getProperty("Ports");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(PORTS, PortService.findAll());
        return Page.ROUTE_CARD.getPage();
    }
}
