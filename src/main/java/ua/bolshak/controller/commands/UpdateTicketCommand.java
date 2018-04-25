package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTicketCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter("idTicket")));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String cruiseId = request.getParameter("CruiseId");
        String ticketTypeId = request.getParameter("TicketTypeId");
        String[] selectedExcursion = request.getParameterValues("selectedExcursions");
        String[] selectedBonuses = request.getParameterValues("selectedBonuses");
        ticket.setName(name);
        ticket.setLastName(lastName);
        ticket.setCruise(CruiseService.findById(Integer.parseInt(cruiseId)));
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(ticketTypeId)));
        ticket.setExcursions(ExcursionService.getListById(selectedExcursion));
        ticket.setBonuses(BonusService.getListBonuses(selectedBonuses));
        TicketService.update(ticket);
        return new ToTicketsPageCommand().execute(request, response);
    }
}
