package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCruiseStatus implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String CRUISE_STATUS_ID = params.getProperty("cruiseStatusId");
    private static final String CRUISE_STATUS_NAME = params.getProperty("CruiseStatusName");
    private static final String SELECTED_CRUISE_STATUS = params.getProperty("SelectedCruiseStatus");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = CruiseStatusService.findById(Integer.parseInt(request.getParameter(CRUISE_STATUS_ID)));
        String name = request.getParameter(CRUISE_STATUS_NAME);
        if (name == null){
            request.setAttribute(SELECTED_CRUISE_STATUS, cruiseStatus.getName());
            request.setAttribute(CRUISE_STATUS_ID, cruiseStatus.getId());
        } else {
            cruiseStatus.setName(name);
            CruiseStatusService.update(cruiseStatus);
        }
        return new ToCruisesPage().execute(request, response);
    }
}
