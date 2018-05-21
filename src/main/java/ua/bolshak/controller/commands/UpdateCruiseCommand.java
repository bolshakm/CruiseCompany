package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.*;
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

public class UpdateCruiseCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String NAME_REGEX = regExResources.getProperty("cruise.name.regexp");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String NAME = params.getProperty("name");
    private static final String FROM = params.getProperty("from");
    private static final String TO = params.getProperty("to");
    private static final String SHIP_ID = params.getProperty("ShipId");
    private static final String CRUISE_STATUS_ID = params.getProperty("CruiseStatusId");
    private static final String ROUTE_ID = params.getProperty("RouteId");
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String ID_CRUISE_STATUS = params.getProperty("idCruiseStatus");
    private static final String ID_ROUTE = params.getProperty("idRoute");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String EMPTY = params.getProperty("empty.string");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        String WRONG_DATE = text.getProperty("wrong.date");
        java.util.Date now = new java.util.Date();
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Cruise cruise = CruiseService.findById(Integer.parseInt(request.getParameter(ID_CRUISE)));
        String name = request.getParameter(NAME);
        String from = request.getParameter(FROM);
        String to = request.getParameter(TO);
        String idShip = request.getParameter(SHIP_ID);
        String idCruiseStatus =request.getParameter(CRUISE_STATUS_ID);
        String idRoute = request.getParameter(ROUTE_ID);
        Date fromDate = null;
        Date toDate = null;
        boolean emptyInput = false;
        if (!from.equals(EMPTY) && !to.equals(EMPTY)) {
            fromDate = Date.valueOf(from);
            toDate = Date.valueOf(to);
            if (now.getTime() > fromDate.getTime() || fromDate.getTime() > toDate.getTime()){
                request.setAttribute(NAME, cruise.getName());
                if (cruise.getShip() != null) {
                    request.setAttribute(ID_SHIP, cruise.getShip().getId());
                }
                if (cruise.getStatus() != null) {
                    request.setAttribute(ID_CRUISE_STATUS, cruise.getStatus().getId());
                }
                if (cruise.getRoute() != null) {
                    request.setAttribute(ID_ROUTE, cruise.getRoute().getId());
                }
                request.setAttribute(ERROR_MASSAGE, WRONG_DATE);
                return new ToCruiseCard().execute(request, response);
            }
        } else {
            emptyInput = true;
        }
        Ship ship = null;
        if (idShip != null) {
            ship = ShipService.findById(Integer.parseInt(idShip));
        } else {
            emptyInput = true;
        }
        CruiseStatus status = null;
        if (idCruiseStatus != null) {
            status = CruiseStatusService.findById(Integer.parseInt(idCruiseStatus));
        } else {
            emptyInput = true;
        }
        Route route = null;
        if (idRoute != null) {
            route = RouteService.findById(Integer.parseInt(idRoute));
        } else {
            emptyInput = true;
        }
        cruise.setName(name);
        cruise.setFrom(fromDate);
        cruise.setTo(toDate);
        cruise.setShip(ship);
        cruise.setStatus(status);
        cruise.setRoute(route);
        if (emptyInput){
            request.setAttribute(NAME, cruise.getName());
            request.setAttribute(FROM, cruise.getFrom());
            request.setAttribute(TO, cruise.getTo());
            if (cruise.getShip() != null) {
                request.setAttribute(ID_SHIP, cruise.getShip().getId());
            }
            if (cruise.getStatus() != null) {
                request.setAttribute(ID_CRUISE_STATUS, cruise.getStatus().getId());
            }
            if (cruise.getRoute() != null) {
                request.setAttribute(ID_ROUTE, cruise.getRoute().getId());
            }
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            return new ToCruiseCard().execute(request, response);
        }
        if (!namePattern.matcher(name).matches()) {
            request.setAttribute(FROM, cruise.getFrom());
            request.setAttribute(TO, cruise.getTo());
            if (cruise.getShip() != null) {
                request.setAttribute(ID_SHIP, cruise.getShip().getId());
            }
            if (cruise.getStatus() != null) {
                request.setAttribute(ID_CRUISE_STATUS, cruise.getStatus().getId());
            }
            if (cruise.getRoute() != null) {
                request.setAttribute(ID_ROUTE, cruise.getRoute().getId());
            }
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            return new ToCruiseCard().execute(request, response);
        }
        CruiseService.update(cruise);
        return new ToCruisesPage().execute(request, response);
    }
}
