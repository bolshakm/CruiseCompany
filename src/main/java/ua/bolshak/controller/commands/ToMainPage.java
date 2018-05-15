package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToMainPage implements ICommand{
    private static RequestParams params = RequestParams.getInstance();
    private static final String USER = params.getProperty("user");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page;
        User user = (User) request.getSession().getAttribute(USER);
        switch (user.getRole().getId()){
            case 1: page = new ToAdministratorPage().execute(request, response);
            break;
            case 2: page = new ToCruisesPage().execute(request, response);
            break;
            case 3: page = new ToShipAdministratorPageCommand().execute(request, response);
            break;
            default: page = new ToStaffPageCommand().execute(request, response);
        }
        return page;
    }
}
