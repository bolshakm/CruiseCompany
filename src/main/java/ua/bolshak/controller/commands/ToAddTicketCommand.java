package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.*;
import ua.bolshak.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToAddTicketCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = new Ticket();
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String cruiseId = request.getParameter("idCruise");
        String idTicketType = request.getParameter("TicketTypeId");
        String [] selectedExcursions = request.getParameterValues("selectedExcursions");
        List<Excursion> excursions = ExcursionService.getListById(selectedExcursions);
        Cruise cruise = CruiseService.findById(Integer.parseInt(cruiseId));
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(idTicketType));
        ticket.setCruise(cruise);
        ticket.setTicketType(ticketType);
        ticket.setExcursions(excursions);
        ticket = TicketService.checkPrice(ticket);
        List<Bonus> bonuses = BonusService.findAllByShipAndTicketType(CruiseService.getFull(cruise).getShip(), ticketType);
        request.setAttribute("login", login);
        request.setAttribute("name", name);
        request.setAttribute("lastName", lastName);
        request.setAttribute("price", ticket.getPrice());
        request.setAttribute("cruise", CruiseService.findById(Integer.parseInt(cruiseId)));
        request.setAttribute("selectedTicketType", TicketTypeService.findById(Integer.parseInt(idTicketType)));
        request.setAttribute("Excursions", excursions);
        request.setAttribute("Bonuses", bonuses);
        return "/jsp/ticketCard.jsp";
    }
}
