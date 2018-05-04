package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUserPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/users.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().getId() == 3){
            request.setAttribute("Users", UserService.findAllByShips(user.getShips()));
        }
        if (user.getRole().getId() == 1) {
            request.setAttribute("Users", UserService.findAll());
            request.setAttribute("Roles", RoleService.findAllMutable());
        }
        return page;
    }
}
