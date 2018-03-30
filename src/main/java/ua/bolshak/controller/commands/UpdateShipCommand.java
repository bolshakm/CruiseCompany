package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        ship.setName(name);
        ship.setNumber(number);
        ship.setNumberOfSeats(Integer.parseInt(numberOfSeats));
        ship.setPricePerSeat(Double.parseDouble(pricePerSeat));
        ShipType shipType = ShipTypeService.findById(Integer.parseInt(shipTypeId));
        ShipService.updateWithBonuses(ship, shipType, bonusesId);
        return new ToShipsPage().execute(request, response);
    }
}
