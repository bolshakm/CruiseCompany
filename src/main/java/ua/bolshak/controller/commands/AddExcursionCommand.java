package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddExcursionCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String NAME = params.getProperty("name");
    private static final String PRICE = params.getProperty("price");
    private static final String ID_PORT = params.getProperty("idPort");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Excursion excursion = new Excursion();
        String name = request.getParameter(NAME);
        String price = request.getParameter(PRICE);
        String idPort = request.getParameter(ID_PORT);
        excursion.setName(name);
        excursion.setPrice(Double.parseDouble(price));
        excursion.setPort(PortService.findById(Integer.parseInt(idPort)));
        ExcursionService.add(excursion);
        return new ToPortsPage().execute(request, response);
    }
}
