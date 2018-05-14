package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class AddCruiseCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String NAME = params.getProperty("name");
    private static final String FROM = params.getProperty("from");
    private static final String TO = params.getProperty("to");
    private static final String SHIP_ID = params.getProperty("ShipId");
    private static final String CRUISE_STATUS_ID = params.getProperty("CruiseStatusId");
    private static final String ROUTE_ID = params.getProperty("RouteId");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = new Cruise();
        String name = request.getParameter(NAME);
        String from = request.getParameter(FROM);
        String to = request.getParameter(TO);
        String idShip = request.getParameter(SHIP_ID);
        String idCruiseStatus = request.getParameter(CRUISE_STATUS_ID);
        String idRoute = request.getParameter(ROUTE_ID);
        cruise.setName(name);
        cruise.setFrom(Date.valueOf(from));
        cruise.setTo(Date.valueOf(to));
        cruise.setShip(ShipService.findById(Integer.parseInt(idShip)));
        cruise.setStatus(CruiseStatusService.findById(Integer.parseInt(idCruiseStatus)));
        cruise.setRoute(RouteService.findById(Integer.parseInt(idRoute)));
        CruiseService.add(cruise);
        return new ToCruisesPage().execute(request, response);
    }
}
