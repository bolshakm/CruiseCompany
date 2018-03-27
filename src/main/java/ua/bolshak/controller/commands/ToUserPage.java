package ua.bolshak.controller.commands;

import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUserPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Users", UserService.findAll());
        request.setAttribute("Roles", RoleService.findAll());
        return "/jsp/users.jsp";
    }
}
