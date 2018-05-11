package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToTicketsPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/tickets.jsp";
        User user = (User) request.getSession().getAttribute("user");
        List<Ticket> tickets = new ArrayList<>();
        switch (user.getRole().getId()) {
            case 1:
                tickets = TicketService.findAllWithFullCruise();
                if (tickets != null) {
                    request.setAttribute("Tickets", tickets);
                }
                request.setAttribute("TicketTypes", TicketTypeService.findAll());
                break;
            case 2:
                tickets = TicketService.findAllByUser(user);
                if (tickets != null) {
                    request.setAttribute("Tickets", tickets);
                } else {
                    request.setAttribute("InfoMassage", "Tickets not found!");
                }
                break;
            case 3:
                tickets = TicketService.findAllByShips(user.getShip());
                if (tickets != null) {
                    request.setAttribute("Tickets", tickets);
                } else {
                    request.setAttribute("InfoMassage", "Tickets not found!");
                }
                break;
        }
        String pageNumber = request.getParameter("pageNumber");
        int count = 5;
        int intPageNumber = 1;
        if (pageNumber != null) {
            intPageNumber = Integer.parseInt(pageNumber);
        }
        int ticketsSize = 0;
        if (tickets != null) {
            ticketsSize = tickets.size();
        }
        int begin = count * intPageNumber - count;
        int end = count * intPageNumber - 1;
        List<Integer> pageNumbers = new ArrayList<>();
        if (intPageNumber == 1 || intPageNumber == 2){
            for (int i = 1; i <= 3; i++) {
                pageNumbers.add(i);
            }
        } else {
            if (intPageNumber == ticketsSize / count + 1 || intPageNumber == ticketsSize / count) {
                for (int i = ticketsSize / count - 1; i <= ticketsSize / count + 1; i++) {
                    pageNumbers.add(i);
                }
            } else {
                for (int i = intPageNumber - 1; i <= intPageNumber + 1; i++) {
                    pageNumbers.add(i + 1);
                }
            }
        }
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("pageNumbers", pageNumbers);
        return page;
    }
}
