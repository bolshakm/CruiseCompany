package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTicketCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String SELECTED_TICKET_TYPE_ID = params.getProperty("selectedTicketTypeId");
    private static final String SELECTED_EXCURSIONS = params.getProperty("selectedExcursions");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String USER = params.getProperty("user");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String NOT_ENOUGH_MONEY = text.getProperty("not.enough.money");
        User user = (User) request.getSession().getAttribute(USER);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String cruiseId = request.getParameter(ID_CRUISE);
        String ticketTypeId = request.getParameter(SELECTED_TICKET_TYPE_ID);
        String[] selectedExcursions = request.getParameterValues(SELECTED_EXCURSIONS);
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setName(name);
        ticket.setLastName(lastName);
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(ticketTypeId)));
        ticket.setCruise(CruiseService.findById(Integer.parseInt(cruiseId)));
        ticket.setExcursions(ExcursionService.getListById(selectedExcursions));
        if (user.getMoney() >= TicketService.checkPrice(ticket).getPrice()) {
            TicketService.buy(TicketService.getEncodingTicket(ticket));
        } else {
            request.setAttribute(ERROR_MASSAGE, NOT_ENOUGH_MONEY);
        }
        user = UserService.findById(user.getId());
        request.getSession().setAttribute(USER, user);
        return new ToMainPage().execute(request, response);
    }
}
