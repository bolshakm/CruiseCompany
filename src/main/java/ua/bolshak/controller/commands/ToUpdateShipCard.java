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
    private static final String SHIP = params.getProperty("Ship");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = ShipService.findById(Integer.parseInt(request.getParameter(ID_SHIP)));
        request.setAttribute(SHIP, ship);
        return new ToShipCardCommand().execute(request, response);
    }
}
