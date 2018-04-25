package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToMainPage implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page = "/jsp/main.jsp";
        request.setAttribute("Cruises", CruiseService.findAll());
        return page;
    }
}
