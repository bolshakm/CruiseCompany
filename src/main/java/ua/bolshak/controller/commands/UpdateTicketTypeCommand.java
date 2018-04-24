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
        String id = request.getParameter("idTicketType");
        String name = request.getParameter("name");
        String[] selecteBonuses = request.getParameterValues("selectedBonuses");

        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(id));
        return new ToTicketsPageCommand().execute(request, response);
    }
}
