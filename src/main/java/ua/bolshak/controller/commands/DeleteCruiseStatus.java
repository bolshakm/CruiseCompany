package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCruiseStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = CruiseStatusService.findById(Integer.parseInt(request.getParameter("cruiseStatusId")));
        CruiseStatusService.delete(cruiseStatus);
        return new ToCruisesPage().execute(request, response);
    }
}
