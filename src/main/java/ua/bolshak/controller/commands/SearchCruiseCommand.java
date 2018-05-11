package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.entity.Route;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.model.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class SearchCruiseCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = new ToAdministratorPage().execute(request, response);
        String name = request.getParameter("name");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String shipNumber = request.getParameter("shipNumber");
        String idCruiseStatus = request.getParameter("cruiseStatusId");
        String routesId = request.getParameter("routesId");
        Date fromDate = null;
        if (from != null && !from.equals("")) {
            fromDate = Date.valueOf(from);
        }
        Date toDate = null;
        if (to != null && !to.equals("")) {
            toDate = Date.valueOf(to);
        }
        Ship ship = null;
        if (shipNumber != null && !shipNumber.equals("")) {
            ship = ShipService.findByNumber(shipNumber);
        }
        CruiseStatus cruiseStatus = null;
        if (idCruiseStatus != null && !idCruiseStatus.equals("")) {
            cruiseStatus = CruiseStatusService.findById(Integer.parseInt(idCruiseStatus));
        }
        Route route = null;
        if (routesId != null  && !routesId.equals("")) {
            route = RouteService.findById(Integer.parseInt(routesId));
        }
        Cruise cruise = new Cruise();
        cruise.setName(name);
        cruise.setFrom(fromDate);
        cruise.setTo(toDate);
        cruise.setShip(ship);
        cruise.setStatus(cruiseStatus);
        cruise.setRoute(route);
        request.setAttribute("Cruises", CruiseService.searchCruise(cruise));
        return page;
    }
}
