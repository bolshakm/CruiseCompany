package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
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

public class AddShipCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regEx = RegExResources.getInstance();
    private static final String WRONG_INPUT = text.getProperty("wrong.input");
    private static final String WRONG_SHIP_TYPE = text.getProperty("select.ship.type");
    private static final String NAME_REGEX = regEx.getProperty("ship.name.regexp");
    private static final String COUNT_OF_SEATS_REGEX = regEx.getProperty("ship.count.of.seats.regexp");
    private static final String PRICE = regEx.getProperty("price.regexp");
    private static final String NUMBER_REGEX = regEx.getProperty("ship.number.regexp");
    private static final String NAME = params.getProperty("name");
    private static final String SHIP = params.getProperty("Ship");
    private static final String SHIP_NUMBER = params.getProperty("shipNumber");
    private static final String NUMBER_OF_SEATS = params.getProperty("numberOfSeats");
    private static final String PRICE_PER_SEATS = params.getProperty("pricePerSeat");
    private static final String ID_SHIP_TYPE = params.getProperty("idShipType");
    private static final String SELECTED_BONUSES = params.getProperty("selectedBonuses");
    private static final String SELECTED_TICKET_TYPE = params.getProperty("selectedTicketTypes");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Pattern numberPattern = Pattern.compile(NUMBER_REGEX);
        Pattern countOfSeatsPattern = Pattern.compile(COUNT_OF_SEATS_REGEX);
        Pattern pricePattern = Pattern.compile(PRICE);
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
        if (countOfSeatsPattern.matcher(numberOfSeats).matches()) {
            ship.setNumberOfSeats(Integer.parseInt(numberOfSeats));
        } else {
            ship.setNumberOfSeats(0);
        }
        if (pricePattern.matcher(pricePerSeat).matches()) {
            ship.setPricePerSeat(Double.parseDouble(pricePerSeat));
        } else {
            ship.setPricePerSeat(0);
        }
        if (idShipType != null){
            ship.setType(ShipTypeService.findById(Integer.parseInt(idShipType)));
        } else {
            ship.setType(null);
        }
        List<Bonus> bonuses = BonusService.getListBonuses(idSelectedBonuses);
        if (!namePattern.matcher(name).matches()){
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            request.setAttribute(SHIP, ship);
            return new ToShipCardCommand().execute(request, response);
        }
        if (!numberPattern.matcher(shipNumber).matches()){
            ship.setNumber(null);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            request.setAttribute(SHIP, ship);
            return new ToShipCardCommand().execute(request, response);
        }
        if (ship.getNumberOfSeats() <= 0){
            ship.setNumberOfSeats(0);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            request.setAttribute(SHIP, ship);
            return new ToShipCardCommand().execute(request, response);
        }
        if (ship.getPricePerSeat() <= 0){
            ship.setPricePerSeat(0);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            request.setAttribute(SHIP, ship);
            return new ToShipCardCommand().execute(request, response);
        }
        if (ship.getType() == null){
            request.setAttribute(ERROR_MASSAGE, WRONG_SHIP_TYPE);
            request.setAttribute(SHIP, ship);
            return new ToShipCardCommand().execute(request, response);
        }
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
