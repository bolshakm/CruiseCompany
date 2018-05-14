package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateShipType implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String SHIP_TYPE_ID = params.getProperty("ShipTypeId");
    private static final String SHIP_TYPE_NAME = params.getProperty("ShipTypeName");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ShipType shipType = ShipTypeService.findById(Integer.parseInt(request.getParameter(SHIP_TYPE_ID)));
        String name = request.getParameter(SHIP_TYPE_NAME);
        if (name == null){
            request.setAttribute(SHIP_TYPE_ID, shipType.getId());
            request.setAttribute(SHIP_TYPE_NAME, shipType.getName());
        } else {
            shipType.setName(name);
            ShipTypeService.update(shipType);
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
