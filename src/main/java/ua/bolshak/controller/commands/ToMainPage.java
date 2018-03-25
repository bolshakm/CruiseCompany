package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToMainPage implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page = "/jsp/main.jsp";
        request.setAttribute("Cruises", CruiseService.findAll());
        return page;
    }
}
