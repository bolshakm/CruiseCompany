package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddCruiseStatus implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String CRUISE_NAME_REGEX = regExResources.getProperty("cruise.name.regexp");
    private static final String CRUISE_STATUS_NAME = params.getProperty("CruiseStatusName");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
//    private static final String SELECTED_CRUISE_STATUS = params.getProperty("SelectedCruiseStatus");
    private static final String WRONG_INPUT = text.getProperty("wrong.input");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CruiseStatus cruiseStatus = new CruiseStatus();
        Pattern namePattern = Pattern.compile(CRUISE_NAME_REGEX);
        String name = request.getParameter(CRUISE_STATUS_NAME);
        if (namePattern.matcher(name).matches()){
            cruiseStatus.setName(name);
            CruiseStatusService.add(cruiseStatus);
        } else {
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
        }
        return new ToCruisesPage().execute(request, response);
    }
}
