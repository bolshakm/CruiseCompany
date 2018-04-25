package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateTicketCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       Ticket ticket = TicketService.findById(Integer.parseInt(request.getParameter("idTicket")));
       request.setAttribute("idTicket", ticket.getId());
       request.setAttribute("login", ticket.getUser().getLogin());
       request.setAttribute("name", ticket.getName());
       request.setAttribute("lastName", ticket.getLastName());
       request.setAttribute("Cruises", CruiseService.findAll());
       request.setAttribute("idCruise", ticket.getCruise().getId());
       request.setAttribute("TicketTypes", TicketTypeService.findAll());
       request.setAttribute("idTicketType", ticket.getTicketType().getId());
       request.setAttribute("Excursions", ExcursionService.findAllByCruse(CruiseService.getFull(ticket.getCruise())));
       request.setAttribute("selectedExcursions", ExcursionService.findAllByTicket(ticket));
       request.setAttribute("Bonuses", BonusService.findAllByShip(ticket.getCruise().getShip()));
       request.setAttribute("selectedBonuses", ticket.getBonuses());
        return "/jsp/ticketCard.jsp";
    }
}
