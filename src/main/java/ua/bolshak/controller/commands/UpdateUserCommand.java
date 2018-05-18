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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateUserCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String LOGIN_REGEX = regExResources.getProperty("login.regexp");
    private static final String MONEY_REGEX = regExResources.getProperty("money.regexp");
    private static final String PASSWORD_REGEX = regExResources.getProperty("password.regexp");
    private static final String NAME_REGEX = regExResources.getProperty("name.regexp");
    private static final String LAST_NAME_REGEX = regExResources.getProperty("last.name.regexp");
    private static final String EMAIL_REGEX = regExResources.getProperty("email.regexp");
    private static final String WRONG_INPUT = text.getProperty("wrong.input");
    private static final String WRONG_LOGIN = text.getProperty("wrong.login");
    private static final String WRONG_PASSWORD = text.getProperty("wrong.password");
    private static final String WRONG_EMAIL = text.getProperty("wrong.email");
    private static final String USER = params.getProperty("User");
    private static final String LOGIN = params.getProperty("login");
    private static final String PASSWORD = params.getProperty("password");
    private static final String PASSWORD_CONFIRM = params.getProperty("passwordConfirm");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String EMAIL = params.getProperty("email");
    private static final String ID_ROLE = params.getProperty("idRole");
    private static final String SESSION_USER = params.getProperty("user");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String NOT_ENOUGH_MONEY = text.getProperty("not.enough.money");
    private static final String ID_USER = params.getProperty("idUser");
    private static final String MONEY = params.getProperty("money");
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String EMPTY_STRING = params.getProperty("empty.string");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page;
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(SESSION_USER);
        Pattern loginPattern = Pattern.compile(LOGIN_REGEX);
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Pattern lastNamePattern = Pattern.compile(LAST_NAME_REGEX);
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Pattern moneyPattern = Pattern.compile(MONEY_REGEX);
        User user = UserService.findById(Integer.parseInt(request.getParameter(ID_USER)));
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
        String email = request.getParameter(EMAIL);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String money = request.getParameter(MONEY);
        String idRole = request.getParameter(ID_ROLE);
        String idShip = request.getParameter(ID_SHIP);
        boolean wrongInput = false;
        String errorMassage = null;
        if (loginPattern.matcher(login).matches()) {
            if (user.getLogin().equals(login) || UserService.findByLogin(login) == null) {
                user.setLogin(login);
            } else {
                user.setLogin(null);
                errorMassage = WRONG_LOGIN;
                wrongInput = true;
            }
        } else {
            user.setLogin(null);
            wrongInput = true;
        }
        if (password == null || passwordConfirm == null){
            password = user.getPassword();
            passwordConfirm = user.getPassword();
        }
        if (passwordPattern.matcher(password).matches() && passwordPattern.matcher(passwordConfirm).matches()) {
            if (password.equals(passwordConfirm)){
                user.setPassword(password);
            } else {
                user.setPassword(null);
                errorMassage = WRONG_PASSWORD;
                wrongInput = true;
            }
        } else {
            user.setPassword(null);
            wrongInput = true;
        }
        if (namePattern.matcher(name).matches()) {
            user.setName(name);
        } else {
            user.setName(null);
            wrongInput = true;
        }
        if (lastNamePattern.matcher(lastName).matches()) {
            user.setLastName(lastName);
        } else {
            user.setLastName(null);
            wrongInput = true;
        }
        if (emailPattern.matcher(email).matches()) {
            if (user.getEmail().equals(email) || UserService.findByEmail(email) == null) {
                user.setEmail(email);
            } else {
                errorMassage = WRONG_EMAIL;
                wrongInput = true;
            }
        } else {
            user.setEmail(null);
            wrongInput = true;
        }
        if (idRole != null) {
            user.setRole(RoleService.findById(Integer.parseInt(idRole)));
        } else {
            user.setRole(null);
            wrongInput = true;
        }
        if (idShip != null) {
            user.setShip(ShipService.findById(Integer.parseInt(idShip)));
        } else {
            user.setShip(null);
            wrongInput = true;
        }
        if (!wrongInput) {
            UserService.update(user);
        } else {
            if (errorMassage != null) {
                request.setAttribute(ERROR_MASSAGE, errorMassage);
            } else {
                request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            }
            request.setAttribute(USER, user);
            return new ToUpdateUserCardCommand().execute(request, response);
        }
        if (sessionUser.getRole().getId() == 1) {
            if (!money.equals(EMPTY_STRING) && moneyPattern.matcher(money).matches() && Double.parseDouble(money) > 0) {
                double moneyForTransfer = Double.parseDouble(money);
                if (sessionUser.getMoney() >= moneyForTransfer) {
                    UserService.transferMoneyFromAdministrator(user, moneyForTransfer);
                    session.setAttribute(SESSION_USER, UserService.findById(1));
                } else {
                    request.setAttribute(ERROR_MASSAGE, NOT_ENOUGH_MONEY);
                    return new ToUpdateUserCardCommand().execute(request, response);
                }
            }
            page = new ToUserPage().execute(request, response);
        } else {
            request.getSession().setAttribute(SESSION_USER, user);
            page = new ToMainPage().execute(request, response);
        }
        return page;
    }
}
