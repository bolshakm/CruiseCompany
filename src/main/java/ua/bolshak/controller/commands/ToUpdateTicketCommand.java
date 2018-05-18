package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateTicketCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_TICKET = params.getProperty("idTicket");
    private static final String EXCURSIONS = params.getProperty("Excursions");
    private static final String TICKET = params.getProperty("Ticket");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter(ID_TICKET)));
       request.setAttribute(EXCURSIONS, ExcursionService.findAllByCruse(CruiseService.getFull(ticket.getCruise())));
       request.setAttribute(TICKET, ticket);
       return new ToTicketCardCommand().execute(request, response);
    }
}
