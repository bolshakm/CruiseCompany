package ua.bolshak.controller.commands;

import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToShipCardCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("ShipTypes", ShipTypeService.findAll());
        request.setAttribute("Bonuses", BonusService.findAll());
        request.setAttribute("TicketTypes", TicketTypeService.findAll());
        return "/jsp/shipCard.jsp";
    }
}
