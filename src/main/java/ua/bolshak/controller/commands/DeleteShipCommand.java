package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.ShipService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteShipCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter("idShip")));
        ShipService.delete(ship);
        return new ToShipsPage().execute(request, response);
    }
}
