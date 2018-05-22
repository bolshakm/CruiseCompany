package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddPortCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String PORT_NAME_REGEX = regExResources.getProperty("port.name.regexp");
    private static final String CITY_NAME_REGEX = regExResources.getProperty("city.name.regexp");
    private static final String COUNTRY_NAME_REGEX = regExResources.getProperty("country.name.regexp");
    private static final String PART_NAME = params.getProperty("PortName");
    private static final String CITY_NAME = params.getProperty("CityName");
    private static final String PORT = params.getProperty("Port");
    private static final String COUNTRY_NAME = params.getProperty("CountryName");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Port port = new Port();
        Pattern namePattern = Pattern.compile(PORT_NAME_REGEX);
        Pattern cityPatter = Pattern.compile(CITY_NAME_REGEX);
        Pattern countryPattern = Pattern.compile(COUNTRY_NAME_REGEX);
        String portName = request.getParameter(PART_NAME);
        String cityName = request.getParameter(CITY_NAME);
        String countryName = request.getParameter(COUNTRY_NAME);
        boolean wrongInput = false;
        if (namePattern.matcher(portName).matches()) {
            port.setName(portName);
        } else {
            port.setName(null);
            wrongInput = true;
        }
        if (cityPatter.matcher(cityName).matches()) {
            port.setCity(cityName);
        } else {
            port.setCity(null);
            wrongInput = true;
        }
        if (countryPattern.matcher(countryName).matches()) {
            port.setCountry(countryName);
        } else {
            port.setCountry(null);
            wrongInput = true;
        }
        if (!wrongInput){
            PortService.add(PortService.getEncodingPort(port));
        } else {
            request.setAttribute(PORT, PortService.getEncodingPort(port));
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
        }
        return new ToPortsPage().execute(request, response);
    }
}
