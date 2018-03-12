package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "/jsp/main.jsp";
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        User user = UserService.getFullUser(UserService.findByLogin(login));
        request.setAttribute("name", user.getName());
        if (!user.getPassword().equals(password)){
            request.setAttribute("massage","Wrong Login or Password");
            page = "/jsp/login.jsp";
        }
        if (user.getRole().equals(RoleService.findById(1))){
//            page = "/jsp/administrator.jsp";
            page = new ToAdministratorPage().execute(request, response);
        }
        return page;
    }
}
