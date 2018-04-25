package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class AddCruiseCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = new Cruise();
        String name = request.getParameter("name");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String idShip = request.getParameter("ShipId");
        String idCruiseStatus = request.getParameter("CruiseStatusId");
        String idRoute = request.getParameter("RouteId");
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
