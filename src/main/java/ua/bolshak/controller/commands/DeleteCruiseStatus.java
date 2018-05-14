package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCruiseStatus implements ICommand {private static RequestParams params = RequestParams.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = CruiseStatusService.findById(Integer.parseInt(request.getParameter("cruiseStatusId")));
        if (!cruiseStatus.getCruises().isEmpty()){
            CruiseStatusService.delete(cruiseStatus);
        } else {
            request.setAttribute(ERROR_MASSAGE, " The cruise status is used!");
        }
        return new ToCruisesPage().execute(request, response);
    }
}
