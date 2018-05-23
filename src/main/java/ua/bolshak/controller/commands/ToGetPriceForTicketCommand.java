package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.*;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class ToGetPriceForTicketCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String NAME_REGEX = regExResources.getProperty("name.regexp");
    private static final String LAST_NAME_REGEX = regExResources.getProperty("last.name.regexp");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String SELECTED_EXCURSION = params.getProperty("selectedExcursions");
    private static final String BONUSES = params.getProperty("Bonuses");
    private static final String TICKET_TYPE_ID = params.getProperty("TicketTypeId");
    private static final String TICKET = params.getProperty("Ticket");
    private static final String USER = params.getProperty("user");
    private static final String EXCURSIONS = params.getProperty("Excursions");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        User user = (User) request.getSession().getAttribute(USER);
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Pattern lastNamePattern = Pattern.compile(LAST_NAME_REGEX);
        boolean wrongInput = false;
        Ticket ticket = new Ticket();
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String cruiseId = request.getParameter(ID_CRUISE);
        String idTicketType = request.getParameter(TICKET_TYPE_ID);
        String [] selectedExcursions = request.getParameterValues(SELECTED_EXCURSION);
        List<Excursion> excursions = ExcursionService.getListById(selectedExcursions);
        Cruise cruise = CruiseService.findById(Integer.parseInt(cruiseId));
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(idTicketType));
        ticket.setUser(user);
        if (namePattern.matcher(name).matches()) {
            ticket.setName(name);
        } else {
            ticket.setName(null);
            wrongInput = true;
        }
        if (lastNamePattern.matcher(lastName).matches()) {
            ticket.setLastName(lastName);
        } else {
            ticket.setLastName(null);
            wrongInput = true;
        }
        ticket.setCruise(cruise);
        ticket.setTicketType(ticketType);
        ticket.setExcursions(excursions);
        List<Bonus> bonuses = BonusService.findAllByShipAndTicketType(CruiseService.getFull(cruise).getShip(), ticketType);
        request.setAttribute(BONUSES, bonuses);
        if (!wrongInput) {
            ticket = TicketService.checkPrice(TicketService.getEncodingTicket(ticket));
        } else {
            ticket.setExcursions(null);
            request.setAttribute(EXCURSIONS, ExcursionService.findAllByCruse(cruise));
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
        }
        request.setAttribute(TICKET, ticket);

        return new ToTicketCardCommand().execute(request, response);
    }
}
