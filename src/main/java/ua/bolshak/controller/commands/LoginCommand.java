package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        User user = UserService.getFullUser(UserService.findLazyByLogin(login));
        request.setAttribute("name", user.getName());
        if (!user.getPassword().equals(password)){
            request.setAttribute("massage","Wrong Login or Password");
            return "/jsp/login.jsp";
        }
        return "/jsp/main.jsp";
    }
}
