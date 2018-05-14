package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteShipCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ID_SHIP = params.getProperty("idShip");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter(ID_SHIP)));
        if (!CruiseService.checkActive(ship.getCruises())) {
            ShipService.delete(ship);
        } else {
            request.setAttribute(ERROR_MASSAGE, "The ship has active cruise!");
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
