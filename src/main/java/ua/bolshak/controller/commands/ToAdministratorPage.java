package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToAdministratorPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "/jsp/administrator.jsp";
        request.setAttribute("Cruises", CruiseService.findAll());
        request.setAttribute("CruiseStatuses", CruiseStatusService.findAll());
        return page;
    }
}
