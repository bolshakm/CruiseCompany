package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.*;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class UpdateCruiseCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String NAME = params.getProperty("name");
    private static final String FROM = params.getProperty("from");
    private static final String TO = params.getProperty("to");
    private static final String SHIP_ID = params.getProperty("ShipId");
    private static final String CRUISE_STATUS_ID = params.getProperty("CruiseStatusId");
    private static final String ROUTE_ID = params.getProperty("RouteId");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = CruiseService.findById(Integer.parseInt(request.getParameter(ID_CRUISE)));
        String name = request.getParameter(NAME);
        Date from = Date.valueOf(request.getParameter(FROM));
        Date to = Date.valueOf(request.getParameter(TO));
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter(SHIP_ID)));
        CruiseStatus status = CruiseStatusService.findById(Integer.parseInt(request.getParameter(CRUISE_STATUS_ID)));
        Route route = RouteService.findById(Integer.parseInt(request.getParameter(ROUTE_ID)));
        cruise.setName(name);
        cruise.setFrom(from);
        cruise.setTo(to);
        cruise.setShip(ship);
        cruise.setStatus(status);
        cruise.setRoute(route);
        CruiseService.update(cruise);
        return new ToCruisesPage().execute(request, response);
    }
}
