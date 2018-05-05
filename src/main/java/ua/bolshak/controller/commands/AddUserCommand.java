package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserCommand implements  ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String idRole = request.getParameter("idRole");
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(RoleService.findById(Integer.parseInt(idRole)));
        UserService.add(user);
        return new ToUserPage().execute(request, response);
    }
}
