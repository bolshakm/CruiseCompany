package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.*;
import ua.bolshak.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class UpdateCruiseCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = CruiseService.findById(Integer.parseInt(request.getParameter("idCruise")));
        String name = request.getParameter("name");
        Date from = Date.valueOf(request.getParameter("from"));
        Date to = Date.valueOf(request.getParameter("to"));
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter("ShipId")));
        CruiseStatus status = CruiseStatusService.findById(Integer.parseInt(request.getParameter("idCruiseStatus")));
        Route route = RouteService.findById(Integer.parseInt(request.getParameter("RouteId")));
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
