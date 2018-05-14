package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateTicketTypeCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_TICKET_TYPE = params.getProperty("idTicketType");
    private static final String TICKET_TYPE = params.getProperty("TicketType");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(request.getParameter(ID_TICKET_TYPE)));
        request.setAttribute(TICKET_TYPE, ticketType);
        return new ToTicketsPageCommand().execute(request, response);
    }
}
