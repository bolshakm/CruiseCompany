package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddBonusesToTicketTypeByShipCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_TICKET_TYPE = params.getProperty("idTicketType");
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idTicketType = request.getParameter(ID_TICKET_TYPE);
        String idShip = request.getParameter(ID_SHIP);
        String[] selectedBonuses = request.getParameterValues(SELECTED_BONUSES);
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(idTicketType));
        Ship ship = ShipService.findById(Integer.parseInt(idShip));
        List<Bonus> bonuses = BonusService.getListBonuses(selectedBonuses);
        if (!bonuses.isEmpty()) {
            BonusService.editBonusesForShipByTicketType(bonuses, ticketType, ship);
            BonusService.editBonusesForTicketByShipAndTicketType(bonuses, ticketType, ship);
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
