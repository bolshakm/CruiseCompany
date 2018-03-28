package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateShipType implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ShipType shipType = ShipTypeService.findById(Integer.parseInt(request.getParameter("ShipTypeId")));
        String name = request.getParameter("ShipTypeName");
        if (name == null){
            request.setAttribute("ShipTypeId", shipType.getId());
            request.setAttribute("ShipTypeName", shipType.getName());
        } else {
            shipType.setName(name);
            ShipTypeService.update(shipType);
        }
        return new ToShipsPage().execute(request, response);
    }
}
