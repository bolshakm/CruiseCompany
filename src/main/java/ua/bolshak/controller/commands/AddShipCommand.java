package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
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

public class AddShipCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String NAME = params.getProperty("name");
    private static final String SHIP_NUMBER = params.getProperty("shipNumber");
    private static final String NUMBER_OF_SEATS = params.getProperty("numberOfSeats");
    private static final String PRICE_PER_SEATS = params.getProperty("pricePerSeat");
    private static final String ID_SHIP_TYPE = params.getProperty("idShipType");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");
    private static final String SELECTED_TICKET_TYPE = params.getProperty("selectedTicketTypes");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = new Ship();
        String name = request.getParameter(NAME);
        String shipNumber = request.getParameter(SHIP_NUMBER);
        String numberOfSeats = request.getParameter(NUMBER_OF_SEATS);
        String pricePerSeat = request.getParameter(PRICE_PER_SEATS);
        String idShipType = request.getParameter(ID_SHIP_TYPE);
        String[] idSelectedBonuses = request.getParameterValues(SELECTED_BONUSES);
        String[] idSelectedTicketTypes = request.getParameterValues(SELECTED_TICKET_TYPE);
        ship.setName(name);
        ship.setNumber(shipNumber);
        ship.setNumberOfSeats(Integer.parseInt(numberOfSeats));
        ship.setPricePerSeat(Double.parseDouble(pricePerSeat));
        ship.setType(ShipTypeService.findById(Integer.parseInt(idShipType)));
        List<Bonus> bonuses = BonusService.getListBonuses(idSelectedBonuses);
        if (bonuses.isEmpty()){
            ship.setBonuses(BonusService.getListWithEmptyBonus());
        } else {
            ship.setBonuses(bonuses);
        }
        ship.setTicketTypes(TicketTypeService.getListTicketTypes(idSelectedTicketTypes));
        ShipService.add(ship);
        return new ToShipsPageCommand().execute(request, response);
    }
}
