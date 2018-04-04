package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTicketCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter("idTicket")));
        TicketService.delete(ticket);
        return new ToTicketsPage().execute(request, response);
    }
}
