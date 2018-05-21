package ua.bolshak.controller.commands;


import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements ICommand {
    private static final TextResources text = TextResources.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String lang = request.getParameter("lang");
        String locale = null;
        if (lang.equals("en")) {
            locale = "en_US";
            text.setEnglishLocal();
        }
        if (lang.equals("ua")) {
            locale = "uk_UA";
            text.setUkrainianLocal();
        }
        request.getSession().setAttribute("language", locale);
        return "/index.jsp";
    }
}
