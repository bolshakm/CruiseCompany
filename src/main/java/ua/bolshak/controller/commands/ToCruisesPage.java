package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToCruisesPage implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String CRUISES = params.getProperty("Cruises");
    private static final String CRUISE_STATUS = params.getProperty("CruiseStatuses");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(CRUISES, CruiseService.findAll());
        request.setAttribute(CRUISE_STATUS, CruiseStatusService.findAll());
        return "/jsp/cruise.jsp";
    }
}
