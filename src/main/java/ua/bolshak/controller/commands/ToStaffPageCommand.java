package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToStaffPageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String USER = params.getProperty("user");
    private static final String CRUISES = params.getProperty("Cruises");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute(USER);
        request.setAttribute(CRUISES, CruiseService.findAllByShip(user.getShip()));
        return Page.STUFF.getPage();
    }
}
