package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBonusCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String BONUS_NAME = params.getProperty("BonusName");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Bonus bonus = new Bonus();
        String name = request.getParameter(BONUS_NAME);
        bonus.setName(name);
        BonusService.add(bonus);
        return new ToShipsPageCommand().execute(request, response);
    }
}
