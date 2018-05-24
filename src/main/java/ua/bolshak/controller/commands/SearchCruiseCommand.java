package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.*;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class SearchCruiseCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String NAME = params.getProperty("name");
    private static final String FROM = params.getProperty("from");
    private static final String TO = params.getProperty("to");
    private static final String SHIP_NUMBER = params.getProperty("shipNumber");
    private static final String CRUISE_STATUS_ID = params.getProperty("cruiseStatusId");
    private static final String ROUTES_ID = params.getProperty("routesId");
    private static final String CRUISES = params.getProperty("Cruises");
    private static final String EMPTY = params.getProperty("empty.string");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = new ToAdministratorPage().execute(request, response);
        String name = request.getParameter(NAME);
        String from = request.getParameter(FROM);
        String to = request.getParameter(TO);
        String shipNumber = request.getParameter(SHIP_NUMBER);
        String idCruiseStatus = request.getParameter(CRUISE_STATUS_ID);
        String routesId = request.getParameter(ROUTES_ID);
        Date fromDate = null;
        if (from != null && !from.equals(EMPTY)) {
            fromDate = Date.valueOf(from);
        }
        Date toDate = null;
        if (to != null && !to.equals(EMPTY)) {
            toDate = Date.valueOf(to);
        }
        Ship ship = null;
        if (shipNumber != null && !shipNumber.equals(EMPTY)) {
            ship = new Ship();
            ship.setNumber(shipNumber);
        }
        CruiseStatus cruiseStatus = null;
        if (idCruiseStatus != null && !idCruiseStatus.equals(EMPTY)) {
            cruiseStatus = CruiseStatusService.findById(Integer.parseInt(idCruiseStatus));
        }
        Route route = null;
        if (routesId != null  && !routesId.equals(EMPTY)) {
            route = RouteService.findById(Integer.parseInt(routesId));
        }
        Cruise cruise = new Cruise();
        cruise.setName(name);
        cruise.setFrom(fromDate);
        cruise.setTo(toDate);
        cruise.setShip(ship);
        cruise.setStatus(cruiseStatus);
        cruise.setRoute(route);
        request.setAttribute(CRUISES, CruiseService.searchCruise(cruise));
        return page;
    }
}
