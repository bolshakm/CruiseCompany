package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteShipTypeCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String SHIP_TYPE_IS_USED = text.getProperty("ship.type.is.used");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String SHIP_TYPE_ID = params.getProperty("ShipTypeId");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ShipType shipType = ShipTypeService.findById(Integer.parseInt(request.getParameter(SHIP_TYPE_ID)));
        if (shipType.getShips().isEmpty()) {
            ShipTypeService.delete(shipType);
        } else {
            request.setAttribute(ERROR_MASSAGE, SHIP_TYPE_IS_USED);
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
