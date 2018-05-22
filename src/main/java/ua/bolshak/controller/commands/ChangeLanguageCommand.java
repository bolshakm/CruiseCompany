package ua.bolshak.controller.commands;


import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements ICommand {
    private static final TextResources text = TextResources.getInstance();
    private static final RequestParams requestParam = RequestParams.getInstance();
    private static final String LANG = requestParam.getProperty("lang");
    private static final String EN = requestParam.getProperty("en");
    private static final String EN_US = requestParam.getProperty("en_US");
    private static final String UA = requestParam.getProperty("ua");
    private static final String UK_UA = requestParam.getProperty("uk_UA");
    private static final String LANGUAGE = requestParam.getProperty("language");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String lang = request.getParameter(LANG);
        String locale = null;
        if (lang.equals(EN)) {
            locale = EN_US;
            text.setEnglishLocal();
        }
        if (lang.equals(UA)) {
            locale = UK_UA;
            text.setUkrainianLocal();
        }
        request.getSession().setAttribute(LANGUAGE, locale);
        return Page.LOGIN.getPage();
    }
}
