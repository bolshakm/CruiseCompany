package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddShipCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = new Ship();
        ship.setName(request.getParameter("name"));
        ship.setNumber(request.getParameter("shipNumber"));
        ship.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));
        ship.setPricePerSeat(Double.parseDouble(request.getParameter("pricePerSeat")));
        ShipType shipType = ShipTypeService.findById(Integer.parseInt(request.getParameter("idShipType")));
        String [] bonusesId = request.getParameterValues("selectedBonuses");
        ShipService.add(ship, shipType);
        ShipService.addBonusesForShip(ShipService.findByNumber(ship.getNumber()), bonusesId);
        return new ToShipsPage().execute(request, response);
    }
}
