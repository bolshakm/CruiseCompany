package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.entity.Route;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.regex.Pattern;

public class AddCruiseCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String CRUISE_NAME_REGEX = regExResources.getProperty("cruise.name.regexp");
    private static final String NAME = params.getProperty("name");
    private static final String FROM = params.getProperty("from");
    private static final String TO = params.getProperty("to");
    private static final String SHIP_ID = params.getProperty("ShipId");
    private static final String CRUISE = params.getProperty("Cruise");
    private static final String CRUISE_STATUS_ID = params.getProperty("CruiseStatusId");
    private static final String ROUTE_ID = params.getProperty("RouteId");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String WRONG_INPUT = text.getProperty("wrong.input");
    private static final String WRONG_DATE = text.getProperty("wrong.date");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = new Cruise();
        java.util.Date now = new java.util.Date();
        Pattern namePattern = Pattern.compile(CRUISE_NAME_REGEX);
        String name = request.getParameter(NAME);
        String from = request.getParameter(FROM);
        String to = request.getParameter(TO);
        String idShip = request.getParameter(SHIP_ID);
        String idCruiseStatus = request.getParameter(CRUISE_STATUS_ID);
        String idRoute = request.getParameter(ROUTE_ID);
        Date fromDate;
        Date toDate;
        boolean wrongInput = false;
        Ship ship;
        if (namePattern.matcher(name).matches()) {
            cruise.setName(name);
        } else {
            cruise.setName(null);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            wrongInput = true;
        }
        if (idShip != null) {
            ship = ShipService.findById(Integer.parseInt(idShip));
            cruise.setShip(ship);
        } else {
            cruise.setShip(null);
            wrongInput = true;
        }
        CruiseStatus status;
        if (idCruiseStatus != null) {
            status = CruiseStatusService.findById(Integer.parseInt(idCruiseStatus));
            cruise.setStatus(status);
        } else {
            cruise.setStatus(null);
            wrongInput = true;
        }
        Route route;
        if (idRoute != null) {
            route = RouteService.findById(Integer.parseInt(idRoute));
            cruise.setRoute(route);
        } else {
            cruise.setRoute(null);
            wrongInput = true;
        }
        if (!from.equals("") && !to.equals("")) {
            fromDate = Date.valueOf(from);
            toDate = Date.valueOf(to);
            if (now.getTime() <= fromDate.getTime() || fromDate.getTime() <= toDate.getTime()){
                cruise.setFrom(fromDate);
                cruise.setTo(toDate);
            } else {
                cruise.setTo(null);
                cruise.setTo(null);
                request.setAttribute(CRUISE, cruise);
                request.setAttribute(ERROR_MASSAGE, WRONG_DATE);
                wrongInput = true;
            }
        } else {
            wrongInput = true;
        }
        if (wrongInput){
            request.setAttribute(CRUISE, cruise);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            return new ToCruiseCard().execute(request, response);
        }

        CruiseService.add(cruise);
        return new ToCruisesPage().execute(request, response);
    }
}
