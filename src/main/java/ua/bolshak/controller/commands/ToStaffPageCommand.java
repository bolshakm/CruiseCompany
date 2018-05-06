package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToStaffPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("Cruises", CruiseService.findAllByShip(user.getShip()));
        return "/jsp/staffPage.jsp";
    }
}
