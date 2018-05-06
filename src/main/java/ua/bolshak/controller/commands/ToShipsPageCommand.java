package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToShipsPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/ship.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().getId() != 3){
            request.setAttribute("ShipTypes", ShipTypeService.findAll());
            request.setAttribute("Bonuses", BonusService.findAll());
            request.setAttribute("Ships", ShipService.findAll());
        } else {
            List<Ship> ships = new ArrayList<>();
            ships.add(ShipService.findByUser(user));
            request.setAttribute("Ships", ships);
        }
        return page;
    }
}
