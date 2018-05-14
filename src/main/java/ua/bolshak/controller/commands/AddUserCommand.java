package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserCommand implements  ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String LOGIN = params.getProperty("login");
    private static final String PASSWORD = params.getProperty("password");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String EMAIL = params.getProperty("email");
    private static final String ID_ROLE = params.getProperty("idRole");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        String idRole = request.getParameter(ID_ROLE);
        String idShip = request.getParameter(ID_SHIP);
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(RoleService.findById(Integer.parseInt(idRole)));
        user.setShip(ShipService.findById(Integer.parseInt(idShip)));
        UserService.add(user);
        return new ToUserPage().execute(request, response);
    }
}
