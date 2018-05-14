package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRoleCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ROLE_NAME = params.getProperty("RoleName");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = new Role();
        String name = request.getParameter(ROLE_NAME);
        role.setName(name);
        RoleService.add(role);
        return new ToUserPage().execute(request, response);
    }
}
