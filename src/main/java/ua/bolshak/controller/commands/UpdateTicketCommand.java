package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTicketCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_TICKET = params.getProperty("idTicket");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String CRUISE_ID = params.getProperty("CruiseId");
    private static final String TICKET_TYPE_ID = params.getProperty("TicketTypeId");
    private static final String SELECTED_EXCURSION = params.getProperty("selectedExcursions");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter(ID_TICKET)));
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String cruiseId = request.getParameter(CRUISE_ID);
        String ticketTypeId = request.getParameter(TICKET_TYPE_ID);
        String[] selectedExcursion = request.getParameterValues(SELECTED_EXCURSION);
        String[] selectedBonuses = request.getParameterValues(SELECTED_BONUSES);
        ticket.setName(name);
        ticket.setLastName(lastName);
        ticket.setCruise(CruiseService.findById(Integer.parseInt(cruiseId)));
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(ticketTypeId)));
        ticket.setExcursions(ExcursionService.getListById(selectedExcursion));
        ticket.setBonuses(BonusService.getListBonuses(selectedBonuses));
        TicketService.update(ticket);
        return new ToTicketsPageCommand().execute(request, response);
    }
}
