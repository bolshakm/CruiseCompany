package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTicketTypeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TicketType ticketType = new TicketType();
        String name = request.getParameter("TicketTypeName");
        String price = request.getParameter("pricePerTicket");
        ticketType.setName(name);
        ticketType.setPrice(Double.parseDouble(price));
        TicketTypeService.add(ticketType);
        return new ToTicketsPageCommand().execute(request, response);
    }
}
