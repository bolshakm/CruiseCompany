package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.RoleService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToUserCardCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_USER = params.getProperty("idUser");
    private static final String LOGIN = params.getProperty("login");
    private static final String EMAIL = params.getProperty("email");
    private static final String NAME = params.getProperty("name");
    private static final String PASSWORD = params.getProperty("password");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String ROLES = params.getProperty("Roles");
    private static final String ID_ROLE = params.getProperty("idRole");
    private static final String SHIPS = params.getProperty("Ships");
    private static final String ID_SHIP = params.getProperty("idShip");
    private static final String TICKETS = params.getProperty("Tickets");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = UserService.findById(Integer.parseInt(request.getParameter(ID_USER)));
        request.setAttribute(ID_USER, user.getId());
        request.setAttribute(LOGIN, user.getLogin());
        request.setAttribute(EMAIL, user.getEmail());
        request.setAttribute(NAME, user.getName());
        request.setAttribute(PASSWORD, user.getPassword());
        request.setAttribute(LAST_NAME, user.getLastName());
        request.setAttribute(ROLES, RoleService.findAllWithoutUser());
        request.setAttribute(ID_ROLE, user.getRole().getId());
        request.setAttribute(SHIPS, ShipService.findAll());
        request.setAttribute(ID_SHIP, user.getShip().getId());
        request.setAttribute(TICKETS, TicketService.findAllByUser(user));
        return "/jsp/userCard.jsp";
    }
}
