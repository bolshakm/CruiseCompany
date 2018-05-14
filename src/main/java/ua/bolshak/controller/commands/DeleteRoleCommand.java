package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRoleCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String ROLE_HAS_USERS = text.getProperty("role.has.users");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ID_ROLE = params.getProperty("idRole");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = RoleService.findById(Integer.parseInt(request.getParameter(ID_ROLE)));
        if (UserService.findAllByRole(role).isEmpty()) {
            RoleService.delete(role);
        } else {
            request.setAttribute(ERROR_MASSAGE, ROLE_HAS_USERS);
        }
        return new ToUserPage().execute(request, response);
    }
}
