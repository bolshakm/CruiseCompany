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

public class ToUpdateUserCardCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_USER = params.getProperty("idUser");
    private static final String USER = params.getProperty("User");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = UserService.findById(Integer.parseInt(request.getParameter(ID_USER)));
        user.setMoney(0);
        request.setAttribute(USER, user);
        return new ToUserCardCommand().execute(request, response);
    }
}
