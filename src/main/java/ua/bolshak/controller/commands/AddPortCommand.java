package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPortCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String PART_NAME = params.getProperty("PortName");
    private static final String CITY_NAME = params.getProperty("CityName");
    private static final String COUNTRY_NAME = params.getProperty("CountryName");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Port port = new Port();
        String portName = request.getParameter(PART_NAME);
        String cityName = request.getParameter(CITY_NAME);
        String countryName = request.getParameter(COUNTRY_NAME);
        port.setName(portName);
        port.setCity(cityName);
        port.setCountry(countryName);
        PortService.add(port);
        return new ToPortsPage().execute(request, response);
    }
}
