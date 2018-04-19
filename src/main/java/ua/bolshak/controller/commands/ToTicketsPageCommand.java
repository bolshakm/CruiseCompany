package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToTicketsPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().getId() == 1) {
            request.setAttribute("Tickets", TicketService.findAllWithFullCruise());
            request.setAttribute("TicketTypes", TicketTypeService.findAll());
        } else {
            List<Ticket> tickets = TicketService.findAllByUser(user);
            if (tickets != null) {
                request.setAttribute("Tickets", tickets);
            } else {
                request.setAttribute("InfoMassage", "Tickets not found!");
            }
        }

        return "/jsp/tickets.jsp";
    }
}
