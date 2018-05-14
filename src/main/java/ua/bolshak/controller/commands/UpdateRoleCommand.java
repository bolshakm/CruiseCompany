package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRoleCommand implements ICommand{
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_ROLE = params.getProperty("idRole");
    private static final String ROLE_NAME = params.getProperty("RoleName");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = RoleService.findById(Integer.parseInt(request.getParameter(ID_ROLE)));
        String name = request.getParameter(ROLE_NAME);
        if (name == null){
            request.setAttribute(ID_ROLE, role.getId());
            request.setAttribute(ROLE_NAME, role.getName());
        } else {
            role.setName(name);
            RoleService.update(role);
        }
        return new ToUserPage().execute(request, response);
    }
}
