package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regEx = RegExResources.getInstance();
    private static final String LOGIN_REGEX = regEx.getProperty("login.regexp");
    private static final String PASSWORD_REGEX = regEx.getProperty("password.regexp");
    private static final String LOGIN = params.getProperty("Login");
    private static final String PASSWORD = params.getProperty("Password");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String USER = params.getProperty("user");
    private static final String ACTION_LOGIN = params.getProperty("actionLogin");
    private static final String ACTION_REGISTRATION = params.getProperty("actionRegistration");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_LOGIN_OR_PASSWORD = text.getProperty("wrong.login.or.password");
        String WRONG_INPUT = text.getProperty("wrong.input");
        HttpSession session = request.getSession();
        Pattern loginPattern = Pattern.compile(LOGIN_REGEX);
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        String page = null;
        String actionLogin = request.getParameter(ACTION_LOGIN);
        String actionPassword = request.getParameter(ACTION_REGISTRATION);
        if (actionLogin != null) {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            Matcher loginMatcher = loginPattern.matcher(login);
            Matcher passwordMatcher = passwordPattern.matcher(password);
            if (loginMatcher.matches() && passwordMatcher.matches()) {
                User user = UserService.findByLogin(new String(login.getBytes("ISO-8859-1"),"cp1251"));
                if (user == null || !user.getPassword().equals(password)) {
                    request.setAttribute(ERROR_MASSAGE, WRONG_LOGIN_OR_PASSWORD);
                    request.setAttribute(LOGIN, new String(login.getBytes("ISO-8859-1"),"cp1251"));
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
            } else {
                request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
                return "/jsp/login.jsp";
            }
        }
        if (actionPassword != null) {
            page = new ToRegistrationPage().execute(request, response);
        }
        return page;
    }
}
