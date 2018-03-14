package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRoleCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/users.jsp";
        Role role = RoleService.findById(Integer.parseInt(request.getParameter("selectedRole")));
        System.out.println(role);
        return page;
    }
}
