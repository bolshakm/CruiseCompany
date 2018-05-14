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
    private static final String LOGIN = params.getProperty("login");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String CRUISES = params.getProperty("Cruises");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String TICKET_TYPES = params.getProperty("TicketTypes");
    private static final String ID_TICKET_TYPE = params.getProperty("idTicketType");
    private static final String EXCURSIONS = params.getProperty("Excursions");
    private static final String SELECTED_EXCURSIONS = params.getProperty("selectedExcursions");
    private static final String BONUSES = params.getProperty("Bonuses");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter(ID_TICKET)));
       request.setAttribute(ID_TICKET, ticket.getId());
       request.setAttribute(LOGIN, ticket.getUser().getLogin());
       request.setAttribute(NAME, ticket.getName());
       request.setAttribute(LAST_NAME, ticket.getLastName());
       request.setAttribute(CRUISES, CruiseService.findAll());
       request.setAttribute(ID_CRUISE, ticket.getCruise().getId());
       request.setAttribute(TICKET_TYPES, TicketTypeService.findAll());
       request.setAttribute(ID_TICKET_TYPE, ticket.getTicketType().getId());
       request.setAttribute(EXCURSIONS, ExcursionService.findAllByCruse(CruiseService.getFull(ticket.getCruise())));
       request.setAttribute(SELECTED_EXCURSIONS, ExcursionService.findAllByTicket(ticket));
       request.setAttribute(BONUSES, ticket.getBonuses());
        return "/jsp/ticketCard.jsp";
    }
}
