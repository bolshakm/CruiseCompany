package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.TicketType;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToTicketTypeCardCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("idTicketType");
        if (id != null){
            TicketType ticketType = TicketTypeService.findById(Integer.parseInt(id));
            request.setAttribute("TicketType", ticketType);
        }
        request.setAttribute("Bonuses", BonusService.findAll());
        return "/jsp/ticketTypeCard.jsp";
    }
}
