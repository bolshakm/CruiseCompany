package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToMainPage implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/main.jsp";
        User user = (User) request.getSession().getAttribute("user");
        switch (user.getRole().getId()){
            case 1: page = new ToAdministratorPage().execute(request, response);
            break;
            case 2: request.setAttribute("Cruises", CruiseService.findAll());
            break;
            case 3: page = new ToShipAdministratorPageCommand().execute(request, response);
            break;
            default: page = new ToStaffPageCommand().execute(request, response);
        }
        return page;
    }
}
