package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTicketCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String cruiseId = request.getParameter("idCruise");
        String ticketTypeId = request.getParameter("selectedTicketTypeId");
        String[] selectedExcursions = request.getParameterValues("selectedExcursions");
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setName(name);
        ticket.setLastName(lastName);
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(ticketTypeId)));
        ticket.setCruise(CruiseService.findById(Integer.parseInt(cruiseId)));
        ticket.setExcursions(ExcursionService.getListById(selectedExcursions));
        if (user.getMoney() >= TicketService.checkPrice(ticket).getPrice()) {
            System.out.println(user.getMoney());
            System.out.println(ticket.getPrice());
            TicketService.buy(ticket);
        } else {
            request.setAttribute("ErrorMassage", "Not enough money");
        }
        user = UserService.findById(user.getId());
        request.getSession().setAttribute("user", user);
        return new ToMainPage().execute(request, response);
    }
}
