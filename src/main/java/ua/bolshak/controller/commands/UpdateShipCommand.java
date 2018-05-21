package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class UpdateShipCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regEx = RegExResources.getInstance();
    private static final String NAME_REGEX = regEx.getProperty("ship.name.regexp");
    private static final String COUNT_OF_SEATS_REGEX = regEx.getProperty("ship.count.of.seats.regexp");
    private static final String PRICE = regEx.getProperty("price.regexp");
    private static final String NUMBER_REGEX = regEx.getProperty("ship.number.regexp");
    private static final String NAME = params.getProperty("name");
    private static final String SHIP = params.getProperty("Ship");
    private static final String SHIP_NUMBER = params.getProperty("shipNumber");
    private static final String NUMBER_OF_SEATS = params.getProperty("numberOfSeats");
    private static final String ID_SHIP_TYPE = params.getProperty("idShipType");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");
    private static final String SELECTED_TICKET_TYPE = params.getProperty("selectedTicketTypes");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String EMPTY_STRING = params.getProperty("empty.string");
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String PRICE_PER_SEAT = params.getProperty("pricePerSeat");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter(ID_SHIP)));
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Pattern numberPattern = Pattern.compile(NUMBER_REGEX);
        Pattern countOfSeatsPattern = Pattern.compile(COUNT_OF_SEATS_REGEX);
        Pattern pricePattern = Pattern.compile(PRICE);
        String name = request.getParameter(NAME);
        String number = request.getParameter(SHIP_NUMBER);
        String numberOfSeats = request.getParameter(NUMBER_OF_SEATS);
        String pricePerSeat = request.getParameter(PRICE_PER_SEAT);
        String shipTypeId = request.getParameter(ID_SHIP_TYPE);
        String [] bonusesId = request.getParameterValues(SELECTED_BONUSES);
        String[] ticketTypesId = request.getParameterValues(SELECTED_TICKET_TYPE);
//        ship.setName(name);
//        ship.setNumber(number);
//        ship.setNumberOfSeats(Integer.parseInt(numberOfSeats));
//        ship.setPricePerSeat(Double.parseDouble(pricePerSeat));
//        ship.setType(ShipTypeService.findById(Integer.parseInt(shipTypeId)));
//        List<Bonus> bonuses = BonusService.getListBonuses(bonusesId);
        boolean wrongInput = false;
        if (namePattern.matcher(name).matches()) {
            ship.setName(name);
        } else {
            ship.setName(null);
            wrongInput = true;
        }
        if (numberPattern.matcher(number).matches()) {
            ship.setNumber(number);
        } else {
            ship.setNumber(null);
            wrongInput = true;
        }
        if (countOfSeatsPattern.matcher(numberOfSeats).matches() && Integer.parseInt(numberOfSeats) > 0) {
            ship.setNumberOfSeats(Integer.parseInt(numberOfSeats));
        } else {
            ship.setNumberOfSeats(0);
            wrongInput = true;
        }
        if (!pricePerSeat.equals(EMPTY_STRING) && pricePattern.matcher(pricePerSeat).matches() && Double.parseDouble(pricePerSeat) > 0) {
            ship.setPricePerSeat(Double.parseDouble(pricePerSeat));
        } else {
            ship.setPricePerSeat(0);
            wrongInput = true;
        }
        if (shipTypeId != null) {
            ship.setType(ShipTypeService.findById(Integer.parseInt(shipTypeId)));
        } else {
            ship.setType(null);
            wrongInput = true;
        }
        List<Bonus> bonuses = BonusService.getListBonuses(bonusesId);
        if (bonuses.isEmpty()) {
            ship.setBonuses(BonusService.getListWithEmptyBonus());
        } else {
            ship.setBonuses(bonuses);
        }
        if (ticketTypesId != null) {
            ship.setTicketTypes(TicketTypeService.getListTicketTypes(ticketTypesId));
        } else {
            ship.setTicketTypes(TicketTypeService.getListWithStandardTicketTypes());
        }
        if (wrongInput) {
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            request.setAttribute(SHIP, ship);
            return new ToShipCardCommand().execute(request, response);
        } else {
            ShipService.update(ship);
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
