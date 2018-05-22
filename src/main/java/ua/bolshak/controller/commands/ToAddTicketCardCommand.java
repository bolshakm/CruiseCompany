package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToAddTicketCardCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String USER = params.getProperty("user");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String TICKET_TYPE = params.getProperty("TicketTypes");
    private static final String EXCURSIONS = params.getProperty("Excursions");
    private static final String TICKET = params.getProperty("Ticket");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(USER);
        String idCruise = request.getParameter(ID_CRUISE);
        Cruise cruise = CruiseService.findById(Integer.parseInt(idCruise));
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setName(user.getName());
        ticket.setLastName(user.getLastName());
        ticket.setCruise(cruise);
        request.setAttribute(TICKET, ticket);
        request.setAttribute(TICKET_TYPE, TicketTypeService.findAllByShip(cruise.getShip()));
        request.setAttribute(EXCURSIONS, ExcursionService.findAllByCruse(cruise));
        return Page.TICKET_CARD.getPage();
    }
}
