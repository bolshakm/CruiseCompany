package ua.bolshak.controller.commands;

import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToShipCardCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String SHIP_TYPES = params.getProperty("ShipTypes");
    private static final String BONUSES = params.getProperty("Bonuses");
    private static final String TICKET_TYPES = params.getProperty("TicketTypes");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(SHIP_TYPES, ShipTypeService.findAll());
        request.setAttribute(BONUSES, BonusService.findAll());
        request.setAttribute(TICKET_TYPES, TicketTypeService.findAll());
        return "/jsp/shipCard.jsp";
    }
}
