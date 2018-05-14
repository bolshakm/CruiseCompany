package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddShipTypeCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String SHIP_TYPE_NAME = params.getProperty("ShipTypeName");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ShipType shipType = new ShipType();
        String name = request.getParameter(SHIP_TYPE_NAME);
        shipType.setName(name);
        ShipTypeService.add(shipType);
        return new ToShipsPageCommand().execute(request, response);
    }
}
