package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddBonusCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String BONUS_NAME = params.getProperty("BonusName");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String BONUS_NAME_REGEX = regExResources.getProperty("bonus.name.regexp");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Bonus bonus = new Bonus();
        Pattern namePattern = Pattern.compile(BONUS_NAME_REGEX);
        String name = request.getParameter(BONUS_NAME);
        if (namePattern.matcher(name).matches()) {
            bonus.setName(name);
            BonusService.add(bonus);
        } else {
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
