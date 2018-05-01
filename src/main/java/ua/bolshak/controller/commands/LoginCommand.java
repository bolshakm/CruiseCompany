package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String page = null;
        String button = request.getParameter("button");
        switch (button) {
            case "Login":
                String login = request.getParameter("Login");
                String password = request.getParameter("Password");
                User user = UserService.findByLogin(login);
                if (user == null || !user.getPassword().equals(password)) {
                    request.setAttribute("ErrorMassage", "Wrong login or password!");
                    request.setAttribute("Login", login);
                    request.setAttribute("Password", password);
                    page = "/jsp/login.jsp";
                    break;
                }
                session.setAttribute("user", user);
                    if (user.getRole().equals(RoleService.findById(1))) {
                        page = new ToAdministratorPage().execute(request, response);
                        break;
                    }
                    if (user.getRole().equals(RoleService.findById(2))) {
                        page = new ToMainPage().execute(request, response);
                        break;
                    }
                    if (user.getRole().equals(RoleService.findById(3))){
                        page = new ToShipAdministratorPageCommand().execute(request, response);
                        break;
                    }
                    page = new ToStaffPageCommand().execute(request, response);
                break;
            case "Registration":
                page = "/jsp/registration.jsp";
        }
        return page;
    }
}
