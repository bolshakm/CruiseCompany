package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToUserPageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String USER = params.getProperty("user");
    private static final String USERS = params.getProperty("Users");
    private static final String ROLES = params.getProperty("Roles");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(USER);
        List<User> users;
        if (user.getRole().getId() == 3){
            users = UserService.findAllByShip(user.getShip());
            request.setAttribute(USERS, users);
            new PaginationCommand().addPagination(request, 5, users.size(), USERS);
        }
        if (user.getRole().getId() == 1) {
            users = UserService.findAll();
            List<Role> roles = RoleService.findAllMutable();
            request.setAttribute(USERS, users);
            request.setAttribute(ROLES, roles);
            new PaginationCommand().addPagination(request, 5, users.size(), USERS);
            new PaginationCommand().addPagination(request, 5, roles.size(), ROLES);
        }
        return Page.USERS.getPage();
    }
}
