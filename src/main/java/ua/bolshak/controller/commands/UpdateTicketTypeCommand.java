package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateTicketTypeCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String TICKET_TYPE_NAME_REGEX = regExResources.getProperty("ticket.type.name.regexp");
    private static final String PRICE_REGEX = regExResources.getProperty("price.regexp");
    private static final String TICKET_TYPE_NAME = params.getProperty("TicketTypeName");
    private static final String PRICE_PER_TICKET = params.getProperty("pricePerTicket");
    private static final String TICKET_TYPE = params.getProperty("TicketType");
    private static final String ID_TICKET_TYPE = params.getProperty("idTicketType");
    private static final String EMPTY_STRING = params.getProperty("empty.string");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Pattern namePattern = Pattern.compile(TICKET_TYPE_NAME_REGEX);
        Pattern pricePattern = Pattern.compile(PRICE_REGEX);
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(request.getParameter(ID_TICKET_TYPE)));
        String name = request.getParameter(TICKET_TYPE_NAME);
        String price = request.getParameter(PRICE_PER_TICKET);
        boolean wrongInput = false;
        if (namePattern.matcher(name).matches()) {
            ticketType.setName(name);
        } else {
            ticketType.setName(null);
            wrongInput = true;
        }
        if (!price.equals(EMPTY_STRING) && pricePattern.matcher(price).matches()) {
            ticketType.setPrice(Double.parseDouble(price));
        } else {
            ticketType.setPrice(0);
            wrongInput = true;
        }
        if (!wrongInput) {
            TicketTypeService.update(TicketTypeService.getEncodingTicketType(ticketType));
        } else {
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            request.setAttribute(TICKET_TYPE, TicketTypeService.getEncodingTicketType(ticketType));
        }
        return new ToTicketsPageCommand().execute(request, response);
    }
}
