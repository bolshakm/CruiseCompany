package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateExcursionCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String EXCURSION_NAME_REGEX = regExResources.getProperty("excursion.name.regexp");
    private static final String PRICE_REGEX = regExResources.getProperty("price.regexp");
    private static final String NAME = params.getProperty("name");
    private static final String PRICE = params.getProperty("price");
    private static final String ID_PORT = params.getProperty("idPort");
    private static final String ID_EXCURSION = params.getProperty("idExcursion");
    private static final String EXCURSION = params.getProperty("Excursion");
    private static final String EMPTY_STRING = params.getProperty("empty.string");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Excursion excursion = ExcursionService.findById(Integer.parseInt(request.getParameter(ID_EXCURSION)));
        Pattern namePattern = Pattern.compile(EXCURSION_NAME_REGEX);
        Pattern pricePattern = Pattern.compile(PRICE_REGEX);
        String name = request.getParameter(NAME);
        String price = request.getParameter(PRICE);
        String idPort = request.getParameter(ID_PORT);
        if (name == null){
            request.setAttribute(EXCURSION, excursion);
        } else {
            boolean wrongInput = false;
            if (namePattern.matcher(name).matches()) {
                excursion.setName(name);
            } else {
                excursion.setName(null);
                wrongInput = true;
            }
            if (pricePattern.matcher(price).matches() && !price.equals(EMPTY_STRING)) {
                excursion.setPrice(Double.parseDouble(price));
            } else {
                excursion.setPrice(0);
                wrongInput = true;
            }
            if (idPort != null) {
                excursion.setPort(PortService.findById(Integer.parseInt(idPort)));
            } else {
                excursion.setPort(null);
                wrongInput = true;
            }
            if (!wrongInput) {
                ExcursionService.update(ExcursionService.getEncodingExcirsion(excursion));
            } else {
                request.setAttribute(EXCURSION, ExcursionService.getEncodingExcirsion(excursion));
                request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            }
        }
        return new ToPortsPage().execute(request, response);
    }
}
