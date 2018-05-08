package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRoleCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = RoleService.findById(Integer.parseInt(request.getParameter("idRole")));
        if (UserService.findAllByRole(role).isEmpty()) {
            RoleService.delete(role);
        } else {
            request.setAttribute("ErrorMassage", "Yhe role has users and can not be deleted!");
        }
        return new ToUserPage().execute(request, response);
    }
}
