package ua.bolshak.controller.commands;

import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToShipsPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("ShipTypes", ShipTypeService.findAll());
        request.setAttribute("Bonuses", BonusService.findAll());
        request.setAttribute("Ships", ShipService.findAll());
        return "/jsp/ship.jsp";
    }
}