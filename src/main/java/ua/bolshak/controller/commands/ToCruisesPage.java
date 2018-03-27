package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToCruisesPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Cruises", CruiseService.findAll());
        request.setAttribute("CruiseStatuses", CruiseStatusService.findAll());
        return "/jsp/cruise.jsp";
    }
}
