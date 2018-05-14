package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateShipCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String NAME = params.getProperty("name");
    private static final String SHIP_NUMBER = params.getProperty("shipNumber");
    private static final String NUMBER_OF_SEATS = params.getProperty("numberOfSeats");
    private static final String PRICE_PER_SEAT = params.getProperty("pricePerSeat");
    private static final String ID_SHIP_TYPE = params.getProperty("idShipType");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");
    private static final String SELECTED_TICKET_TYPE = params.getProperty("selectedTicketTypes");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter(ID_SHIP)));
        String name = request.getParameter(NAME);
        String number = request.getParameter(SHIP_NUMBER);
        String numberOfSeats = request.getParameter(NUMBER_OF_SEATS);
        String pricePerSeat = request.getParameter(PRICE_PER_SEAT);
        String shipTypeId = request.getParameter(ID_SHIP_TYPE);
        String [] bonusesId = request.getParameterValues(SELECTED_BONUSES);
        String[] ticketTypesId = request.getParameterValues(SELECTED_TICKET_TYPE);
        ship.setName(name);
        ship.setNumber(number);
        ship.setNumberOfSeats(Integer.parseInt(numberOfSeats));
        ship.setPricePerSeat(Double.parseDouble(pricePerSeat));
        ship.setType(ShipTypeService.findById(Integer.parseInt(shipTypeId)));
        List<Bonus> bonuses = BonusService.getListBonuses(bonusesId);
        if (bonuses.isEmpty()){
            ship.setBonuses(BonusService.getListWithEmptyBonus());
        } else {
            ship.setBonuses(bonuses);
        }
        List<TicketType> ticketTypes = TicketTypeService.getListTicketTypes(ticketTypesId);
        if (ticketTypes.isEmpty()){
            ship.setTicketTypes(TicketTypeService.getListWhithStandartTicketTypes());
        } else {
            ship.setTicketTypes(ticketTypes);
        }
        ShipService.update(ship);
        return new ToShipsPageCommand().execute(request, response);
    }
}
