package ua.bolshak.controller.commands;

import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToTicketsPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Tickets", TicketService.findAllWithFullCruise());
        request.setAttribute("TicketTypes", TicketTypeService.findAll());

        return "/jsp/tickets.jsp";
    }
}
