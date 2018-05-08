package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTicketTypeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(request.getParameter("idTicketType")));
        if (!ticketType.getTickets().isEmpty()){
            if (ticketType.getId() != 1) {
                TicketTypeService.delete(ticketType);
            } else {
                request.setAttribute("ErrorMassage", "The standard can not be deleted!");
            }
        } else {
             request.setAttribute("ErrorMassage", "The ticket type has tickets!");
        }
        return new ToTicketsPageCommand().execute(request, response);
    }
}
