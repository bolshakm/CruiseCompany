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

public class ToAddBonusesToTicketTypeByShipCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/ticketTypeCard.jsp";
        String idShip = request.getParameter("idShip");
        String idTicketType = request.getParameter("idTicketType");
        Ship ship = ShipService.findById(Integer.parseInt(idShip));
        TicketType ticketType = TicketTypeService.findById(Integer.parseInt(idTicketType));
        List<Bonus> selectedBonuses = BonusService.findAllByShipAndTicketType(ship, ticketType);
        request.setAttribute("TicketType", ticketType);
        request.setAttribute("Ship", ship);
        request.setAttribute("selectedBonuses",selectedBonuses);
        request.setAttribute("Bonuses", BonusService.findAllByShip(ship));
        return page;
    }
}
