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

public class UpdateBonusCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String BONUS_NAME_REGEX = regExResources.getProperty("bonus.name.regexp");
    private static final String ID_BONUS = params.getProperty("idBonus");
    private static final String BONUS = params.getProperty("Bonus");
    private static final String BONUS_NAME = params.getProperty("BonusName");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String WRONG_INPUT = text.getProperty("wrong.input");
        Pattern namePattern = Pattern.compile(BONUS_NAME_REGEX);
        Bonus bonus = BonusService.findById(Integer.parseInt(request.getParameter(ID_BONUS)));
        String name = request.getParameter(BONUS_NAME);
        if (name == null){
            request.setAttribute(BONUS, bonus);
        } else {
            if (namePattern.matcher(name).matches()) {
                bonus.setName(name);
                BonusService.update(BonusService.getEncodingBonus(bonus));
            } else {
                request.setAttribute(BONUS, BonusService.getEncodingBonus(bonus));
                request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
            }
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
