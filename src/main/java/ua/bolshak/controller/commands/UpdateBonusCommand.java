package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateBonusCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_BONUS = params.getProperty("idBonus");
    private static final String BONUS_NAME = params.getProperty("BonusName");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Bonus bonus = BonusService.findById(Integer.parseInt(request.getParameter(ID_BONUS)));
        String name = request.getParameter(BONUS_NAME);
        if (name == null){
            request.setAttribute(ID_BONUS, bonus.getId());
            request.setAttribute(BONUS_NAME, bonus.getName());
        } else {
            bonus.setName(name);
            BonusService.update(bonus);
        }
        return new ToShipsPageCommand().execute(request, response);
    }
}
