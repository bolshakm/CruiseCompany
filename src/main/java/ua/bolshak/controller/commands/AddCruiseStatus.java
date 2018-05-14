package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCruiseStatus implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String CRUISE_STATUS_NAME = params.getProperty("CruiseStatusName");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = new CruiseStatus();
        String name = request.getParameter(CRUISE_STATUS_NAME);
        cruiseStatus.setName(name);
        CruiseStatusService.add(cruiseStatus);
        return new ToCruisesPage().execute(request, response);
    }
}
