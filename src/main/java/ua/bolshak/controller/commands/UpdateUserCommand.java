package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page;
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        User user = UserService.findById(Integer.parseInt(request.getParameter("idUser")));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String money = request.getParameter("money");
        String idRole = request.getParameter("idRole");
        String idShip = request.getParameter("ShipId");
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
                request.setAttribute("ErrorMassage", "Wrong password");
                request.setAttribute("idUser", user.getId());
                return new ToUserCardCommand().execute(request, response);
            }

        }
        if (login != null && !login.equals(user.getLogin())) {
            if (UserService.findByLogin(login) == null) {
                user.setLogin(login);
            } else {
                request.setAttribute("ErrorMassage", "Wrong login");
                request.setAttribute("idUser", user.getId());
                return new ToUserCardCommand().execute(request, response);
            }
        }
        if (email != null && !email.equals(user.getEmail())) {
            if (UserService.findByEmail(email) == null) {
                user.setEmail(email);
            } else {
                request.setAttribute("ErrorMassage", "Wrong email");
                request.setAttribute("idUser", user.getId());
                return new ToUserCardCommand().execute(request, response);
            }
        }
        UserService.update(user);
        if (sessionUser.getRole().getId() == 1) {
            if (money != null) {
                double moneyForTransfer = Double.parseDouble(money);
                if (sessionUser.getMoney() >= moneyForTransfer) {
                    UserService.transferMoneyFromAdministrator(user, moneyForTransfer);
                    session.setAttribute("user", UserService.findById(1));
                } else {
                    request.setAttribute("ErrorMassage", "Not enough money!");
                    return new ToUserCardCommand().execute(request, response);
                }
            }
            page = new ToUserPage().execute(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            page = new ToMainPage().execute(request, response);
        }
        return page;
    }
}
