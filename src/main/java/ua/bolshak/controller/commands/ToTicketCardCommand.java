package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToTicketCardCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String USER = params.getProperty("user");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String LOGIN = params.getProperty("login");
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String CRUISE = params.getProperty("cruise");
    private static final String TICKET_TYPE = params.getProperty("TicketTypes");
    private static final String EXCURSIONS = params.getProperty("Excursions");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(USER);
        String idCruise = request.getParameter(ID_CRUISE);
        Cruise cruise = CruiseService.findById(Integer.parseInt(idCruise));
        request.setAttribute(LOGIN, user.getLogin());
        request.setAttribute(NAME, user.getName());
        request.setAttribute(LAST_NAME, user.getLastName());
        request.setAttribute(CRUISE, cruise);
        request.setAttribute(TICKET_TYPE, TicketTypeService.findAllByShip(cruise.getShip()));
        request.setAttribute(EXCURSIONS, ExcursionService.findAllByCruse(cruise));
        return "/jsp/ticketCard.jsp";
    }
}
