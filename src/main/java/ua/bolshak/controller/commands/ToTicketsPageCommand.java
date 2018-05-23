package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToTicketsPageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String USER = params.getProperty("user");
    private static final String TICKETS = params.getProperty("Tickets");
    private static final String TICKET_TYPES = params.getProperty("TicketTypes");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String TICKET_NOT_FOUND = text.getProperty("ticket.not.found");
        User user = (User) request.getSession().getAttribute(USER);
        List<Ticket> tickets = new ArrayList<>();
        switch (user.getRole().getId()) {
            case 1:
                tickets = TicketService.findAllWithFullCruise();
                if (tickets != null) {
                    request.setAttribute(TICKETS, tickets);
                }
                List<TicketType> ticketTypes = TicketTypeService.findAll();
                request.setAttribute(TICKET_TYPES, ticketTypes);
                new PaginationCommand().addPagination(request, 5, ticketTypes.size(), TICKET_TYPES);
                break;
            case 2:
                tickets = TicketService.findAllByUser(user);
                if (tickets != null) {
                    request.setAttribute(TICKETS, tickets);
                } else {
                    request.setAttribute(ERROR_MASSAGE, TICKET_NOT_FOUND);
                }
                break;
            case 3:
                tickets = TicketService.findAllByShips(user.getShip());
                if (tickets != null) {
                    request.setAttribute(TICKETS, tickets);
                } else {
                    request.setAttribute(ERROR_MASSAGE, TICKET_NOT_FOUND);
                }
                break;
        }
        if (tickets != null) {
            new PaginationCommand().addPagination(request, 5, tickets.size(), TICKETS);
        }
        return Page.TICKETS.getPage();
    }
}
