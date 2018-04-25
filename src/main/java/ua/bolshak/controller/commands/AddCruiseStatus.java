package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCruiseStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = new CruiseStatus();
        String name = request.getParameter("CruiseStatusName");
        cruiseStatus.setName(name);
        CruiseStatusService.add(cruiseStatus);
        return new ToCruisesPage().execute(request, response);
    }
}
