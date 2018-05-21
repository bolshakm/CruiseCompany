package ua.bolshak.controller.commands;

import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionsForRouteCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ACTION_UPDATE = params.getProperty("actionUpdate");
    private static final String ACTION_DELETE = params.getProperty("actionDelete");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = null;
        String actionUpdate = request.getParameter(ACTION_UPDATE);
        String actionDelete = request.getParameter(ACTION_DELETE);
        if (actionUpdate != null){
            page = new ToUpdateRouteCommand().execute(request, response);
        }
        if (actionDelete != null){
            page = new DeleteRouteCommand().execute(request, response);
        }
        return page;
    }
}
