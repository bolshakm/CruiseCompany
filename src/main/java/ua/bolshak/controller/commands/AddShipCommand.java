package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.ShipTypeService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddShipCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ship ship = new Ship();
        String name = request.getParameter("name");
        String shipNumber = request.getParameter("shipNumber");
        String numberOfSeats = request.getParameter("numberOfSeats");
        String pricePerSeat = request.getParameter("pricePerSeat");
        String idShipType = request.getParameter("idShipType");
        String[] idSelectedBonuses = request.getParameterValues("selectedBonuses");
        String[] idSelectedTicketTypes = request.getParameterValues("selectedTicketTypes");
        ship.setName(name);
        ship.setNumber(shipNumber);
        ship.setNumberOfSeats(Integer.parseInt(numberOfSeats));
        ship.setPricePerSeat(Double.parseDouble(pricePerSeat));
        ship.setType(ShipTypeService.findById(Integer.parseInt(idShipType)));
        ship.setBonuses(BonusService.getListBonuses(idSelectedBonuses));
        ship.setTicketTypes(TicketTypeService.getListTicketTypes(idSelectedTicketTypes));
        ShipService.add(ship);
        return new ToShipsPage().execute(request, response);
    }
}
