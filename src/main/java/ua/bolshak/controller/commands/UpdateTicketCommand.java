package ua.bolshak.controller.commands;

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
        String[] selectedBonuses = request.getParameterValues("selectedBonuses");
        String[] selectedExcursion = request.getParameterValues("selectedExcursions");
        ticket.setName(name);
        ticket.setLastName(lastName);
        ticket.setCruise(CruiseService.findById(Integer.parseInt(cruiseId)));
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(ticketTypeId)));
        ticket.setBonuses(BonusService.getListBonuses(selectedBonuses));
        ticket.setExcursions(ExcursionService.getListById(selectedExcursion));
        TicketService.update(ticket);
        return new ToTicketsPage().execute(request, response);
    }
}
