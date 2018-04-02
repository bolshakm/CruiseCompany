package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.ShipTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteShipTypeCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ShipType shipType = ShipTypeService.findById(Integer.parseInt(request.getParameter("ShipTypeId")));
        ShipTypeService.delete(shipType);
        return new ToShipsPage().execute(request, response);
    }
}