package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToCruiseCard implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Ships", ShipService.findAll());
        request.setAttribute("CruiseStatuses", CruiseStatusService.findAll());
        request.setAttribute("Ports", PortService.findAll());
        return "/jsp/cruiseCard.jsp";
    }
}
