package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;
import ua.bolshak.util.StatisticMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToStatisticPageCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String CRUISE_COMES_MONEY = params.getProperty("cruise.comes.money");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(CRUISE_COMES_MONEY, new StatisticMap(CruiseService.getMapAllCruiseComesMoney()));
        return Page.STATISTIC.getPage();
    }
}
