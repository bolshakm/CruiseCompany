package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUserCardCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = UserService.findById(Integer.parseInt(request.getParameter("idUser")));
        request.setAttribute("idUser", user.getId());
        request.setAttribute("login", user.getLogin());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("name", user.getName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("money", user.getMoney());
        request.setAttribute("Roles", RoleService.findAll());
        request.setAttribute("idRole", user.getRole().getId());
        request.setAttribute("Tickets", TicketService.findAllByUser(user));
        return "/jsp/userCard.jsp";
    }
}