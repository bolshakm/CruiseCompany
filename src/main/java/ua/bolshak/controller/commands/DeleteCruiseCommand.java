package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCruiseCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String CRUISE_IS_ACTIVE = text.getProperty("cruise.is.active");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = CruiseService.findById(Integer.parseInt(request.getParameter(ID_CRUISE)));
        if (!CruiseService.checkActive(cruise)) {
            CruiseService.delete(cruise);
        } else {
            request.setAttribute(ERROR_MASSAGE, CRUISE_IS_ACTIVE);
        }
        return new ToCruisesPage().execute(request, response);
    }
}
