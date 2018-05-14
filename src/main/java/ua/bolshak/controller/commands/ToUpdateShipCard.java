package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateShipCard implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String NAME = params.getProperty("name");
    private static final String SHIP_NUMBER = params.getProperty("shipNumber");
    private static final String NUMBER_OF_SEATS = params.getProperty("numberOfSeats");
    private static final String PRICE_PER_SEATS = params.getProperty("pricePerSeat");
    private static final String ID_SHIP_TYPE = params.getProperty("idShipType");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");
    private static final String SELECTED_TICKET_TYPES = params.getProperty("selectedTicketTypes");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter(ID_SHIP)));
        request.setAttribute(ID_SHIP,ship.getId());
        request.setAttribute(NAME, ship.getName());
        request.setAttribute(SHIP_NUMBER, ship.getNumber());
        request.setAttribute(NUMBER_OF_SEATS, ship.getNumberOfSeats());
        request.setAttribute(PRICE_PER_SEATS, ship.getPricePerSeat());
        request.setAttribute(ID_SHIP_TYPE, ship.getType().getId());
        request.setAttribute(SELECTED_BONUSES, ship.getBonuses());
        request.setAttribute(SELECTED_TICKET_TYPES, ship.getTicketTypes());
        return new ToShipCardCommand().execute(request, response);
    }
}
