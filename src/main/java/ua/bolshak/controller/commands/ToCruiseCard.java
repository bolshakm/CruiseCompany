package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToCruiseCard implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String SHIPS = params.getProperty("Ships");
    private static final String CRUISE_STATUS = params.getProperty("CruiseStatuses");
    private static final String ROUTES = params.getProperty("Routes");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(SHIPS, ShipService.findAll());
        request.setAttribute(CRUISE_STATUS, CruiseStatusService.findAll());
        request.setAttribute(ROUTES, RouteService.findAll());
        return Page.CRUISE_CARD.getPage();
    }
}
