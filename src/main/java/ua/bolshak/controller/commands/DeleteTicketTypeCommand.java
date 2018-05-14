package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTicketTypeCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ID_TICKET_TYPE = params.getProperty("idTicketType");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(request.getParameter(ID_TICKET_TYPE)));
        if (!ticketType.getTickets().isEmpty()){
            if (ticketType.getId() != 1) {
                TicketTypeService.delete(ticketType);
            } else {
                request.setAttribute(ERROR_MASSAGE, "The standard can not be deleted!");
            }
        } else {
             request.setAttribute(ERROR_MASSAGE, "The ticket type has tickets!");
        }
        return new ToTicketsPageCommand().execute(request, response);
    }
}
