package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToAddBonusesToTicketTypeByShipCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String ID_TICKET_TYPE = params.getProperty("idTicketType");
    private static final String TICKET_TYPE = params.getProperty("TicketType");
    private static final String SHIP = params.getProperty("Ship");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");
    private static final String BONUSES = params.getProperty("Bonuses");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idShip = request.getParameter(ID_SHIP);
        String idTicketType = request.getParameter(ID_TICKET_TYPE);
        Ship ship = ShipService.findById(Integer.parseInt(idShip));
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(idTicketType));
        List<Bonus> selectedBonuses = BonusService.findAllByShipAndTicketType(ship, ticketType);
        request.setAttribute(TICKET_TYPE, ticketType);
        request.setAttribute(SHIP, ship);
        request.setAttribute(SELECTED_BONUSES,selectedBonuses);
        request.setAttribute(BONUSES, BonusService.findAllByShip(ship));
        return Page.TICKET_TYPE_CARD.getPage();
    }
}
