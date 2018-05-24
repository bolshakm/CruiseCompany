package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationPage implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ROLES = params.getProperty("Roles");
    private static final String SHIPS = params.getProperty("Ships");
    private static final String USER = params.getProperty("user");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(USER);
        request.getSession().invalidate();
        if (user != null){
            request.setAttribute(ROLES, RoleService.findAll());
            request.setAttribute(SHIPS, ShipService.findAll());
        }
        return Page.REGISTRATION.getPage();
    }
}
