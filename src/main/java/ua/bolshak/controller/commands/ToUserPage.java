package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUserPage implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String USER = params.getProperty("user");
    private static final String USERS = params.getProperty("Users");
    private static final String ROLES = params.getProperty("Roles");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/users.jsp";
        User user = (User) request.getSession().getAttribute(USER);
        if (user.getRole().getId() == 3){
            request.setAttribute(USERS, UserService.findAllByShip(user.getShip()));
        }
        if (user.getRole().getId() == 1) {
            request.setAttribute(USERS, UserService.findAll());
            request.setAttribute(ROLES, RoleService.findAllMutable());
        }
        return page;
    }
}
