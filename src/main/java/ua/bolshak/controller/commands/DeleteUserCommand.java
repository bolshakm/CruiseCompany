package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.UserService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand implements ICommand{
    private static RequestParams params = RequestParams.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ID_USER = params.getProperty("idUser");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = UserService.findById(Integer.parseInt(request.getParameter(ID_USER)));
        if (user.getRole().getId() == 2) {
            if (!TicketService.checkActiveTicketByUser(user)) {
                UserService.delete(user);
            } else {
                request.setAttribute(ERROR_MASSAGE, "The user has active tickets");
            }
        } else {
            UserService.delete(user);
        }
        return new ToUserPage().execute(request, response);
    }
}
