package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddShipTypeCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String SHIP_TYPE_NAME = params.getProperty("ShipTypeName");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String SHIP_TYPE_NAME_REGEX = regExResources.getProperty("ship.type.name.regexp");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        ShipType shipType = new ShipType();
        Pattern namePattern = Pattern.compile(SHIP_TYPE_NAME_REGEX);
        String name = request.getParameter(SHIP_TYPE_NAME);
        if (namePattern.matcher(name).matches()) {
            shipType.setName(name);
            ShipTypeService.add(ShipTypeService.getEncodingShipType(shipType));
        } else {
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
