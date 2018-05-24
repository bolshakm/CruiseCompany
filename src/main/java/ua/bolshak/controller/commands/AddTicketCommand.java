package ua.bolshak.controller.commands;

import org.apache.commons.mail.*;
import org.apache.log4j.Logger;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.*;
import ua.bolshak.properties.RegExResources;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddTicketCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddTicketCommand.class);
    private static RequestParams params = RequestParams.getInstance();
    private static RegExResources regExResources = RegExResources.getInstance();
    private static final String NAME_REGEX = regExResources.getProperty("name.regexp");
    private static final String LAST_NAME_REGEX = regExResources.getProperty("last.name.regexp");
    private static TextResources text = TextResources.getInstance();
    private static final String NAME = params.getProperty("name");
    private static final String LAST_NAME = params.getProperty("lastName");
    private static final String ID_CRUISE = params.getProperty("idCruise");
    private static final String SELECTED_TICKET_TYPE_ID = params.getProperty("selectedTicketTypeId");
    private static final String SELECTED_EXCURSIONS = params.getProperty("selectedExcursions");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String USER = params.getProperty("user");
    private static final String MAIL = "bolshakmv@gmail.com";
    private static final String MAIL_PASSWORD = "1277joker1277";
    private static final String HOST_NAME = "smtp.gmail.com";
    private static final String SUBJECT = "Cruise Company";
    private static final String THANK_YOU = ", thank you for entrusting us with your rest and chose ";
    private static final String EXCURSION_INCLUDE = " Excursions are included in your ticket: \n";
    private static final String NEW_LINE = "\n";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String NOT_ENOUGH_MONEY = text.getProperty("not.enough.money");
        String WRONG_INPUT = text.getProperty("wrong.input");
        Pattern namePattern = Pattern.compile(NAME_REGEX);
        Pattern lastNamePattern = Pattern.compile(LAST_NAME_REGEX);
        User user = (User) request.getSession().getAttribute(USER);
        String name = request.getParameter(NAME);
        String lastName = request.getParameter(LAST_NAME);
        String cruiseId = request.getParameter(ID_CRUISE);
        String ticketTypeId = request.getParameter(SELECTED_TICKET_TYPE_ID);
        String[] selectedExcursions = request.getParameterValues(SELECTED_EXCURSIONS);
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTicketType(TicketTypeService.findById(Integer.parseInt(ticketTypeId)));
        ticket.setCruise(CruiseService.findById(Integer.parseInt(cruiseId)));
        ticket.setExcursions(ExcursionService.getListById(selectedExcursions));
        boolean wrongInput = false;
        if (namePattern.matcher(name).matches()){
            ticket.setName(name);
        } else {
            wrongInput = true;
            ticket.setName(null);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
        }
        if (lastNamePattern.matcher(lastName).matches()){
            ticket.setLastName(lastName);
        } else {
            wrongInput = true;
            ticket.setLastName(null);
            request.setAttribute(ERROR_MASSAGE, WRONG_INPUT);
        }
        if (user.getMoney() >= TicketService.checkPrice(ticket).getPrice()) {
            if (!wrongInput) {
                TicketService.buy(TicketService.getEncodingTicket(ticket));
                sendEmail(ticket);
            }
        } else {
            request.setAttribute(ERROR_MASSAGE, NOT_ENOUGH_MONEY);
        }
        user = UserService.findById(user.getId());
        request.getSession().setAttribute(USER, user);
        return new ToMainPage().execute(request, response);
    }


    private void sendEmail(Ticket ticket){
        try {
            StringBuilder massage = new StringBuilder(ticket.getName() + THANK_YOU + ticket.getCruise().getName() + ".");
            if (!ticket.getExcursions().isEmpty()){
                massage.append(EXCURSION_INCLUDE);
                for (Excursion excursion :
                        ticket.getExcursions()) {
                    massage.append(excursion.getName()).append(NEW_LINE);
                }
            }
            Email email = new SimpleEmail();
            email.setHostName(HOST_NAME);
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(MAIL, MAIL_PASSWORD));
            email.setSSLOnConnect(true);
            email.setFrom(MAIL);
            email.setSubject(SUBJECT);
            email.setMsg(massage.toString());
            email.addTo(ticket.getUser().getEmail());
            email.send();
        } catch (EmailException e) {
            LOGGER.error(e);
        }
    }
}
