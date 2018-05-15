package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCruiseStatusCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String CRUISE_STATUS_IS_USED = text.getProperty("cruise.status.is.used");
    private static final String CRUISE_STATUS_ID = params.getProperty("cruiseStatusId");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = CruiseStatusService.findById(Integer.parseInt(request.getParameter(CRUISE_STATUS_ID)));
        if (cruiseStatus.getCruises().isEmpty()){
            CruiseStatusService.delete(cruiseStatus);
        } else {
            request.setAttribute(ERROR_MASSAGE, CRUISE_STATUS_IS_USED);
        }
        return new ToCruisesPage().execute(request, response);
    }
}
