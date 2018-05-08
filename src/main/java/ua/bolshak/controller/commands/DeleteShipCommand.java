package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteShipCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter("idShip")));
        if (!CruiseService.checkActive(ship.getCruises())) {
            ShipService.delete(ship);
        } else {
            request.setAttribute("ErrorMassage", "The ship has active cruise!");
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
