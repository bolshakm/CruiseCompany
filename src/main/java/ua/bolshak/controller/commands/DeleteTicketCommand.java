package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTicketCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ID_TICKET = params.getProperty("idTicket");
    private static final String TICKET_IS_ACTIVE = text.getProperty("ticket.is.active");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter(ID_TICKET)));
        if (!TicketService.checkActiveTicker(ticket)){
            TicketService.delete(ticket);
        } else {
            request.setAttribute(ERROR_MASSAGE, TICKET_IS_ACTIVE);
        }
        return new ToTicketsPageCommand().execute(request, response);
    }
}
