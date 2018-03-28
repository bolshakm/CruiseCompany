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
        Role role = RoleService.findById(Integer.parseInt(request.getParameter("idRole")));
        String name = request.getParameter("RoleName");
        if (name == null){
            request.setAttribute("idRole", role.getId());
            request.setAttribute("RoleName", role.getName());
        } else {
            role.setName(name);
            RoleService.update(role);
        }
        return new ToUserPage().execute(request, response);
    }
}
