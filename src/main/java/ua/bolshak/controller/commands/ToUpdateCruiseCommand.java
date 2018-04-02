package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateCruiseCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = CruiseService.findById(Integer.parseInt(request.getParameter("idCruise")));
        request.setAttribute("idCruise", cruise.getId());
        request.setAttribute("name", cruise.getName());
        request.setAttribute("from", cruise.getFrom());
        request.setAttribute("to", cruise.getTo());
        request.setAttribute("idShip", cruise.getShip().getId());
        request.setAttribute("Ships", ShipService.findAll());
        request.setAttribute("idCruiseStatus", cruise.getStatus().getId());
        request.setAttribute("CruiseStatuses", CruiseStatusService.findAll());
        request.setAttribute("selectedPorts", cruise.getPorts());
        request.setAttribute("Ports", PortService.findAll());
        return "/jsp/cruiseCard.jsp";
    }
}
