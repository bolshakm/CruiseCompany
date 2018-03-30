package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUpdateShipCard implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter("idShip")));
        request.setAttribute("idShip",ship.getId());
        request.setAttribute("name", ship.getName());
        request.setAttribute("shipNumber", ship.getNumber());
        request.setAttribute("numberOfSeats", ship.getNumberOfSeats());
        request.setAttribute("pricePerSeat", ship.getPricePerSeat());
        request.setAttribute("idShipType", ship.getType().getId());
        request.setAttribute("selectedBonuses", ship.getBonuses());
        return new ToShipCardCommand().execute(request, response);
    }
}
