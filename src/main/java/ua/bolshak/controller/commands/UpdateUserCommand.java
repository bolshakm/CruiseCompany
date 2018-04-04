package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Role;
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
        User user = UserService.findById(Integer.parseInt(request.getParameter("idUser")));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        Double money = Double.parseDouble(request.getParameter("money"));
        Role role = RoleService.findById(Integer.parseInt(request.getParameter("idRole")));
        user.setName(name);
        user.setLastName(lastName);
        user.setRole(role);
        user.setMoney(money);
        UserService.update(user);
        return new ToUserPage().execute(request, response);
    }
}
