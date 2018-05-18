package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToTicketCardCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String CRUISES = params.getProperty("Cruises");
    private static final String TICKET_TYPES = params.getProperty("TicketTypes");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(CRUISES, CruiseService.findAll());
        request.setAttribute(TICKET_TYPES, TicketTypeService.findAll());
        return "/jsp/ticketCard.jsp";
    }
}
