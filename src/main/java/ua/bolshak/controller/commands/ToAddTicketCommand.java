package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.*;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToAddTicketCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String LOGIN = params.getProperty("login");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String SELECTED_EXCURSION = params.getProperty("selectedExcursions");
    private static final String PRICE = params.getProperty("price");
    private static final String CRUISE = params.getProperty("cruise");
    private static final String SELECTED_TICKET_TYPE = params.getProperty("selectedTicketType");
    private static final String EXCURSIONS = params.getProperty("Excursions");
    private static final String BONUSES = params.getProperty("Bonuses");
    private static final String TICKET_TYPE_ID = params.getProperty("TicketTypeId");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ticket ticket = new Ticket();
        String login = request.getParameter(LOGIN);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String cruiseId = request.getParameter(ID_CRUISE);
        String idTicketType = request.getParameter(TICKET_TYPE_ID);
        String [] selectedExcursions = request.getParameterValues(SELECTED_EXCURSION);
        List<Excursion> excursions = ExcursionService.getListById(selectedExcursions);
        Cruise cruise = CruiseService.findById(Integer.parseInt(cruiseId));
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(idTicketType));
        ticket.setCruise(cruise);
        ticket.setTicketType(ticketType);
        ticket.setExcursions(excursions);
        ticket = TicketService.checkPrice(ticket);
        List<Bonus> bonuses = BonusService.findAllByShipAndTicketType(CruiseService.getFull(cruise).getShip(), ticketType);
        request.setAttribute(LOGIN, login);
        request.setAttribute(NAME, name);
        request.setAttribute(LAST_NAME, lastName);
        request.setAttribute(PRICE, ticket.getPrice());
        request.setAttribute(CRUISE, CruiseService.findById(Integer.parseInt(cruiseId)));
        request.setAttribute(SELECTED_TICKET_TYPE, TicketTypeService.findById(Integer.parseInt(idTicketType)));
        request.setAttribute(EXCURSIONS, excursions);
        request.setAttribute(BONUSES, bonuses);
        return "/jsp/ticketCard.jsp";
    }
}
