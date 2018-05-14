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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String WRONG_PASSWORD = text.getProperty("wrong.password");
    private static final String WRONG_LOGIN = text.getProperty("wrong.login");
    private static final String WRONG_EMAIL = text.getProperty("wrong.email");
    private static final String NOT_ENOUGH_MONEY = text.getProperty("not.enough.money");
    private static final String USER = params.getProperty("user");
    private static final String ID_USER = params.getProperty("idUser");
    private static final String LOGIN = params.getProperty("login");
    private static final String PASSWORD = params.getProperty("password");
    private static final String PASSWORD_CONFIRM = params.getProperty("passwordConfirm");
    private static final String EMAIL = params.getProperty("email");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String NAME = params.getProperty("name");
    private static final String MONEY = params.getProperty("money");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String ID_ROLE = params.getProperty("idRole");
    private static final String SHIP_ID = params.getProperty("ShipId");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page;
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(USER);
        User user = UserService.findById(Integer.parseInt(request.getParameter(ID_USER)));
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
        String email = request.getParameter(EMAIL);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String money = request.getParameter(MONEY);
        String idRole = request.getParameter(ID_ROLE);
        String idShip = request.getParameter(SHIP_ID);
        if (name != null) {
            user.setName(name);
        }
        if (lastName != null) {
            user.setLastName(lastName);
        }
        if (idRole != null) {
            user.setRole(RoleService.findById(Integer.parseInt(idRole)));
        }
        if (idShip != null) {
            user.setShip(ShipService.findById(Integer.parseInt(idShip)));
        }
        if (sessionUser.getRole().getId() != 1) {
            if (password != null && passwordConfirm != null && !password.equals("") && !passwordConfirm.equals("") && password.equals(passwordConfirm)) {
                user.setPassword(password);
            } else {
                request.setAttribute(ERROR_MASSAGE, WRONG_PASSWORD);
                request.setAttribute(ID_USER, user.getId());
                return new ToUserCardCommand().execute(request, response);
            }

        }
        if (login != null && !login.equals(user.getLogin())) {
            if (UserService.findByLogin(login) == null) {
                user.setLogin(login);
            } else {
                request.setAttribute(ERROR_MASSAGE,  WRONG_LOGIN);
                request.setAttribute(ID_USER, user.getId());
                return new ToUserCardCommand().execute(request, response);
            }
        }
        if (email != null && !email.equals(user.getEmail())) {
            if (UserService.findByEmail(email) == null) {
                user.setEmail(email);
            } else {
                request.setAttribute(ERROR_MASSAGE, WRONG_EMAIL);
                request.setAttribute(ID_USER, user.getId());
                return new ToUserCardCommand().execute(request, response);
            }
        }
        UserService.update(user);
        if (sessionUser.getRole().getId() == 1) {
            if (money != null) {
                double moneyForTransfer = Double.parseDouble(money);
                if (sessionUser.getMoney() >= moneyForTransfer) {
                    UserService.transferMoneyFromAdministrator(user, moneyForTransfer);
                    session.setAttribute(USER, UserService.findById(1));
                } else {
                    request.setAttribute(ERROR_MASSAGE, NOT_ENOUGH_MONEY);
                    return new ToUserCardCommand().execute(request, response);
                }
            }
            page = new ToUserPage().execute(request, response);
        } else {
            request.getSession().setAttribute(USER, user);
            page = new ToMainPage().execute(request, response);
        }
        return page;
    }
}
