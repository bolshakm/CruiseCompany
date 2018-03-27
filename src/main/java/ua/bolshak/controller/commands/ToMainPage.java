package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToMainPage implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page = "/jsp/main.jsp";
        request.setAttribute("Cruises", CruiseService.findAll());
        request.setAttribute("Ships", ShipService.findAll());
        request.setAttribute("TicketStandardPrice", TicketTypeService.findById(1));
        return page;
    }
}
