package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToTicketCardCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        String idCruise = request.getParameter("idCruise");
        Cruise cruise = CruiseService.findById(Integer.parseInt(idCruise));
        request.setAttribute("login", user.getLogin());
        request.setAttribute("name", user.getName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("cruise", cruise);
        request.setAttribute("TicketTypes", TicketTypeService.findAll());
        request.setAttribute("Excursions", ExcursionService.findAllByLazyCruse(cruise));
        return "/jsp/ticketCard.jsp";
    }
}
