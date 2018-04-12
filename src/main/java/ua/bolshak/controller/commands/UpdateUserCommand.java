package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page;
        User sessionUser = (User) request.getSession().getAttribute("user");
        User user = UserService.findById(Integer.parseInt(request.getParameter("idUser")));
        String action = request.getParameter("action");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String money = request.getParameter("money");
        String role = request.getParameter("idRole");
        user.setName(name);
        user.setLastName(lastName);
        if (action.equals("Delete")){
            return new DeleteTicketCommand().execute(request, response);
        }
        if (role != null) {
            user.setRole(RoleService.findById(Integer.parseInt(role)));
        }
        if (money != null) {
            user.setMoney(Double.parseDouble(money));
        }
        if (password != null && passwordConfirm != null && !password.equals("") && !passwordConfirm.equals("")){
            if (password.equals(passwordConfirm)){
                user.setPassword(password);
            } else {
                request.setAttribute("ErrorMassage", "Wrong password");
                request.setAttribute("idUser", user.getId());
                return new ToUserCardCommand().execute(request, response);
            }
        }
        if (login != null && !login.equals(user.getLogin())){
            if (UserService.findByLogin(login) == null){
                user.setLogin(login);
            } else {
                request.setAttribute("ErrorMassage", "Wrong login or email");
                request.setAttribute("idUser", user.getId());
                return new ToUserCardCommand().execute(request, response);
            }
        }
        if (email != null && !email.equals(user.getEmail())){
            if (UserService.findByEmail(email) == null){
                user.setEmail(email);
            } else {
                request.setAttribute("ErrorMassage", "Wrong login or email");
                request.setAttribute("idUser", user.getId());
                return new ToUserCardCommand().execute(request, response);
            }
        }

        UserService.update(user);
        if (sessionUser.getRole().getId() == 1){
            page = new ToUserPage().execute(request, response);
        } else {
            page = new ToMainPage().execute(request, response);
        }

        return page;
    }
}
