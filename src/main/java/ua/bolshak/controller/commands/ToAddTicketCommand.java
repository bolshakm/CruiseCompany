package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.TicketTypeService;

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
        ticket.setCruise(CruiseService.findById(Integer.parseInt(cruiseId)));
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(idTicketType)));
        ticket.setExcursions(excursions);
        ticket = TicketService.checkPrice(ticket);
        request.setAttribute("login", login);
        request.setAttribute("name", name);
        request.setAttribute("lastName", lastName);
        request.setAttribute("price", ticket.getPrice());
        request.setAttribute("cruise", CruiseService.findById(Integer.parseInt(cruiseId)));
        request.setAttribute("selectedTicketType", TicketTypeService.findById(Integer.parseInt(idTicketType)));
        request.setAttribute("Excursions", excursions);
        return "/jsp/ticketCard.jsp";
    }
}
