package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String BUTTON = params.getProperty("button");
    private static final String LOGIN = params.getProperty("Login");
    private static final String PASSWORD = params.getProperty("Password");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String USER = params.getProperty("user");
    private static final String REGISTRATION = params.getProperty("Registration");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String page = null;
        String button = request.getParameter(BUTTON);
        if (button.equals(LOGIN)) {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            User user = UserService.findByLogin(login);
            if (user == null || !user.getPassword().equals(password)) {
                request.setAttribute(ERROR_MASSAGE, "Wrong login or password!");
                request.setAttribute(LOGIN, login);
                request.setAttribute(PASSWORD, password);
                return "/jsp/login.jsp";
            }
            session.setAttribute(USER, user);
            if (user.getRole().equals(RoleService.findById(1))) {
                return new ToAdministratorPage().execute(request, response);
            }
            if (user.getRole().equals(RoleService.findById(2))) {
                return new ToMainPage().execute(request, response);
            }
            if (user.getRole().equals(RoleService.findById(3))) {
                return new ToShipAdministratorPageCommand().execute(request, response);
            }
            page = new ToStaffPageCommand().execute(request, response);
        }
        if (button.equals(REGISTRATION)) {
            page = new ToRegistrationPage().execute(request, response);
        }
        return page;
    }
}
