package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTicketTypeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(request.getParameter("idTicketType")));
        String name = request.getParameter("TicketTypeName");
        if (name == null){
            request.setAttribute("TicketTypeName", ticketType.getName());
            request.setAttribute("idTicketType", ticketType.getId());
        } else {
            ticketType.setName(name);
            TicketTypeService.update(ticketType);
        }
        return new ToTicketsPage().execute(request, response);
    }
}
