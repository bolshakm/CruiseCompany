package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToShipsPageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String USER = params.getProperty("user");
    private static final String SHIP_TYPES = params.getProperty("ShipTypes");
    private static final String BONUSES = params.getProperty("Bonuses");
    private static final String SHIPS = params.getProperty("Ships");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/ship.jsp";
        User user = (User) request.getSession().getAttribute(USER);
        if (user.getRole().getId() != 3){
            request.setAttribute(SHIP_TYPES, ShipTypeService.findAll());
            request.setAttribute(BONUSES, BonusService.findAll());
            request.setAttribute(SHIPS, ShipService.findAll());
        } else {
            List<Ship> ships = new ArrayList<>();
            ships.add(ShipService.findByUser(user));
            request.setAttribute(SHIPS, ships);
        }
        return page;
    }
}
