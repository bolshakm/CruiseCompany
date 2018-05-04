package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddBonusesToTicketTypeByShipCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idTicketType = request.getParameter("idTicketType");
        String idShip = request.getParameter("idShip");
        String[] selectedBonuses = request.getParameterValues("selectedBonuses");
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(idTicketType));
        Ship ship = ShipService.findById(Integer.parseInt(idShip));
        List<Bonus> bonuses = BonusService.getListBonuses(selectedBonuses);
        BonusService.editBonusesForShipByTicketType(bonuses, ticketType, ship);
        return new ToShipsPageCommand().execute(request, response);
    }
}
