package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddShipTypeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ShipType shipType = new ShipType();
        String name = request.getParameter("ShipTypeName");
        shipType.setName(name);
        ShipTypeService.add(shipType);
        return new ToShipsPageCommand().execute(request, response);
    }
}
