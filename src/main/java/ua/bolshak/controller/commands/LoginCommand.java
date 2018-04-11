package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String page = "/jsp/main.jsp";
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        String button = request.getParameter("button");
        switch (button) {
            case "Login":
                User user = UserService.findByLogin(login);
                if (user == null || !user.getPassword().equals(password)) {
                    request.setAttribute("Login", login);
                    request.setAttribute("Password", password);
                    page = "/jsp/login.jsp";
                    break;
                }
                session.setAttribute("user", user);
                request.setAttribute("name", user.getName());
                if (user.getRole().equals(RoleService.findById(1))) {
                    page = new ToAdministratorPage().execute(request, response);
                }
                if (user.getRole().equals(RoleService.findById(2))){
                    page = new ToMainPage().execute(request,response);
                }
                break;
            case "Registration":
                page = "/jsp/registration.jsp";
        }
        return page;
    }
}
