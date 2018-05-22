package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateTicketCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String NAME_REGEX = regExResources.getProperty("name.regexp");
    private static final String LAST_NAME_REGEX = regExResources.getProperty("last.name.regexp");
    private static final String ID_TICKET = params.getProperty("idTicket");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String TICKET_TYPE_ID = params.getProperty("TicketTypeId");
    private static final String TICKET = params.getProperty("Ticket");
    private static final String SELECTED_EXCURSION = params.getProperty("selectedExcursions");
    private static final String EXCURSIONS = params.getProperty("Excursions");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter(ID_TICKET)));
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Pattern lastNamePattern = Pattern.compile(LAST_NAME_REGEX);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String ticketTypeId = request.getParameter(TICKET_TYPE_ID);
        String[] selectedExcursion = request.getParameterValues(SELECTED_EXCURSION);
        boolean wrongInput = false;
        if (namePattern.matcher(name).matches()) {
            ticket.setName(name);
        } else {
            ticket.setName(null);
            wrongInput = true;
        }
        if (lastNamePattern.matcher(lastName).matches()) {
            ticket.setLastName(lastName);
        } else {
            ticket.setLastName(null);
            wrongInput = true;
        }
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(ticketTypeId)));
        ticket.setExcursions(ExcursionService.getListById(selectedExcursion));
        if (!wrongInput) {
            TicketService.update(TicketService.getEncodingTicket(ticket));
        } else {
            request.setAttribute(TICKET, TicketService.getEncodingTicket(ticket));
            request.setAttribute(EXCURSIONS, ExcursionService.findAllByCruse(CruiseService.getFull(ticket.getCruise())));
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            return new ToTicketCardCommand().execute(request, response);
        }
        return new ToTicketsPageCommand().execute(request, response);
    }
}
