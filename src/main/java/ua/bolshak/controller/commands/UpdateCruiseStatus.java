package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCruiseStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = CruiseStatusService.findById(Integer.parseInt(request.getParameter("cruiseStatusId")));
        String name = request.getParameter("CruiseStatusName");
        if (name == null){
            request.setAttribute("SelectedCruiseStatus", cruiseStatus.getName());
            request.setAttribute("cruiseStatusId", cruiseStatus.getId());
        } else {
            cruiseStatus.setName(name);
            CruiseStatusService.update(cruiseStatus);
        }
        return new ToCruisesPage().execute(request, response);
    }
}
