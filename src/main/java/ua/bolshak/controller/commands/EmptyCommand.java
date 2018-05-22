package ua.bolshak.controller.commands;


import ua.bolshak.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ERROR.getPage();
    }
}
