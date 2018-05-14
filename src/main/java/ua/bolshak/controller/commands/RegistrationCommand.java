package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements ICommand{
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String WRONG_LOGIN = text.getProperty("wrong.login");
    private static final String WRONG_PASSWORD = text.getProperty("wrong.password");
    private static final String WRONG_EMAIL = text.getProperty("wrong.email");
    private static final String LOGIN = params.getProperty("login");
    private static final String PASSWORD = params.getProperty("password");
    private static final String PASSWORD_CONFIRM = params.getProperty("passwordConfirm");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String EMAIL = params.getProperty("email");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String USER = params.getProperty("user");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(RoleService.findById(2));
        user.setShip(ShipService.getEmptyShip());
        if (!password.equals(passwordConfirm)){
            request.setAttribute(ERROR_MASSAGE, WRONG_PASSWORD);
            request.setAttribute(LOGIN, user.getLogin());
            request.setAttribute(PASSWORD_CONFIRM, user.getPassword());
            request.setAttribute(NAME, user.getName());
            request.setAttribute(LAST_NAME, user.getLastName());
            request.setAttribute(EMAIL, user.getEmail());
            return new ToRegistrationPage().execute(request, response);
        }
        if (UserService.findByLogin(login) != null){
            request.setAttribute(ERROR_MASSAGE, WRONG_LOGIN);
            request.setAttribute(PASSWORD, user.getPassword());
            request.setAttribute(PASSWORD_CONFIRM, user.getPassword());
            request.setAttribute(NAME, user.getName());
            request.setAttribute(LAST_NAME, user.getLastName());
            request.setAttribute(EMAIL, user.getEmail());
            return new ToRegistrationPage().execute(request, response);
        }
        if (UserService.findByEmail(email) != null){
            request.setAttribute(ERROR_MASSAGE, WRONG_EMAIL);
            request.setAttribute(LOGIN, user.getLogin());
            request.setAttribute(PASSWORD, user.getPassword());
            request.setAttribute(PASSWORD_CONFIRM, user.getPassword());
            request.setAttribute(NAME, user.getName());
            request.setAttribute(LAST_NAME, user.getLastName());
            return new ToRegistrationPage().execute(request, response);
        }
        UserService.add(user);
        request.getSession().setAttribute(USER, user);
        return new ToMainPage().execute(request, response);
    }
}
