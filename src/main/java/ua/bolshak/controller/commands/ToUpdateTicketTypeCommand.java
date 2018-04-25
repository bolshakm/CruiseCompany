package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateTicketTypeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(request.getParameter("idTicketType")));
        request.setAttribute("TicketType", ticketType);
        return new ToTicketsPageCommand().execute(request, response);
    }
}
