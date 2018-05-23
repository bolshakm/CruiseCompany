package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

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
        User user = (User) request.getSession().getAttribute(USER);
        if (user.getRole().getId() != 3){
            List<Ship> ships = ShipService.findAll();
            List<Bonus> bonuses = BonusService.findAll();
            List<ShipType> shipTypes = ShipTypeService.findAll();
            request.setAttribute(SHIP_TYPES, shipTypes);
            request.setAttribute(BONUSES, bonuses);
            request.setAttribute(SHIPS, ships);
            new PaginationCommand().addPagination(request, 5, ships.size(), SHIPS);
            new PaginationCommand().addPagination(request, 5, bonuses.size(), BONUSES);
            new PaginationCommand().addPagination(request, 5, shipTypes.size(), SHIP_TYPES);
        } else {
            List<Ship> ships = new ArrayList<>();
            ships.add(ShipService.findByUser(user));
            request.setAttribute(SHIPS, ships);
        }
        return Page.SHIP.getPage();
    }
}
