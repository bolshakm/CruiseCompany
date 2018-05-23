package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateRoleCommand implements ICommand{
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ROLE_NAME_REGEX = regExResources.getProperty("role.name.regexp");
    private static final String ID_ROLE = params.getProperty("idRole");
    private static final String ROLE_NAME = params.getProperty("RoleName");
    private static final String ROLE = params.getProperty("Role");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Pattern namePattern = Pattern.compile(ROLE_NAME_REGEX);
        Role role = RoleService.findById(Integer.parseInt(request.getParameter(ID_ROLE)));
        String name = request.getParameter(ROLE_NAME);
        if (name == null){
            request.setAttribute(ROLE, role);
        } else {
            if (namePattern.matcher(name).matches()) {
                role.setName(name);
                RoleService.update(RoleService.getEncodingRole(role));
            } else {
                request.setAttribute(ROLE, RoleService.getEncodingRole(role));
                request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            }
        }
        return new ToUserPageCommand().execute(request, response);
    }
}
