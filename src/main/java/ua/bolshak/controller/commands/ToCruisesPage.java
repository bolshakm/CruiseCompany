package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.RouteService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToCruisesPage implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String CRUISES = params.getProperty("Cruises");
    private static final String CRUISE_STATUS = params.getProperty("CruiseStatuses");
    private static final String ROUTES = params.getProperty("Routes");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        List<Cruise> cruises;
        if (user.getRole().getId() == 2) {
            cruises = CruiseService.findAllActual();
        } else {
            cruises = CruiseService.findAll();
        }
        request.setAttribute(CRUISES, cruises);
        request.setAttribute(ROUTES, RouteService.findAll());
        request.setAttribute(CRUISE_STATUS, CruiseStatusService.findAll());

        new PaginationCommand().addPagination(request, 5, cruises.size(), CRUISES);
        new PaginationCommand().addPagination(request, 3, CruiseStatusService.findAll().size(), CRUISE_STATUS);
        return Page.CRUISE.getPage();
    }
}
