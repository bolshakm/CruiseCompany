package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateShipCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter("idShip")));
        String name = request.getParameter("name");
        String number = request.getParameter("shipNumber");
        String numberOfSeats = request.getParameter("numberOfSeats");
        String pricePerSeat = request.getParameter("pricePerSeat");
        String shipTypeId = request.getParameter("idShipType");
        String [] bonusesId = request.getParameterValues("selectedBonuses");
        String[] ticketTypesId = request.getParameterValues("selectedTicketTypes");
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
        ship.setTicketTypes(TicketTypeService.getListTicketTypes(ticketTypesId));
        ShipService.update(ship);
        return new ToShipsPageCommand().execute(request, response);
    }
}
