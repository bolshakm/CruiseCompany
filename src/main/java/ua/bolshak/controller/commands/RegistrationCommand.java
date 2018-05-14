package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class RegistrationCommand implements ICommand{
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String LOGIN_REGEX = regExResources.getProperty("login.regexp");
    private static final String PASSWORD_REGEX = regExResources.getProperty("password.regexp");
    private static final String NAME_REGEX = regExResources.getProperty("name.regexp");
    private static final String LAST_NAME_REGEX = regExResources.getProperty("last.name.regexp");
    private static final String EMAIL_REGEX = regExResources.getProperty("email.regexp");
    private static final String WRONG_INPUT = text.getProperty("wrong.input");
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
        Pattern loginPattern = Pattern.compile(LOGIN_REGEX);
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Pattern lastNamePattern = Pattern.compile(LAST_NAME_REGEX);
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
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
        if (!loginPattern.matcher(login).matches()) {
            user.setLogin(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_INPUT, user);
        }
        if (UserService.findByLogin(login) != null) {
            user.setLogin(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_LOGIN, user);
        }
        if (!passwordPattern.matcher(password).matches() || !passwordPattern.matcher(passwordConfirm).matches()) {
            user.setPassword(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_INPUT, user);
        }
        if (!password.equals(passwordConfirm)){
            user.setPassword(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_PASSWORD, user);
        }
        if (!namePattern.matcher(name).matches()) {
            user.setName(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_INPUT, user);
        }
        if (!lastNamePattern.matcher(lastName).matches()) {
            user.setLastName(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_INPUT, user);
        }
        if (!emailPattern.matcher(email).matches()) {
            user.setEmail(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_INPUT, user);
        }
        if (UserService.findByEmail(email) != null) {
            user.setEmail(null);
            return redirectToRegistrationPageWithErrorMassage(request, response, WRONG_EMAIL, user);
        }
        user.setRole(RoleService.findById(2));
        user.setShip(ShipService.getEmptyShip());
        UserService.add(user);
        request.getSession().setAttribute(USER, user);
        return new ToMainPage().execute(request, response);
    }

    private String redirectToRegistrationPageWithErrorMassage(HttpServletRequest request,HttpServletResponse response, String errorMassage, User user) throws IOException, ServletException {
        request.setAttribute(LOGIN, user.getLogin());
        request.setAttribute(PASSWORD, user.getPassword());
        request.setAttribute(PASSWORD_CONFIRM, user.getPassword());
        request.setAttribute(NAME, user.getName());
        request.setAttribute(LAST_NAME, user.getLastName());
        request.setAttribute(EMAIL, user.getEmail());
        request.setAttribute(ERROR_MASSAGE, errorMassage);
        return new ToRegistrationPage().execute(request, response);
    }

}
