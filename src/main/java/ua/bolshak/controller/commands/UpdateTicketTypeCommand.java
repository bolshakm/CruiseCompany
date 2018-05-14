package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTicketTypeCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_TICKET_TYPE = params.getProperty("idTicketType");
    private static final String TICKET_TYPE_NAME = params.getProperty("TicketTypeName");
    private static final String PRICE_PER_TICKET = params.getProperty("pricePerTicket");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter(ID_TICKET_TYPE);
        String name = request.getParameter(TICKET_TYPE_NAME);
        String price = request.getParameter(PRICE_PER_TICKET);
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(id));
        ticketType.setName(name);
        ticketType.setPrice(Double.parseDouble(price));
        TicketTypeService.update(ticketType);
        return new ToTicketsPageCommand().execute(request, response);
    }
}
