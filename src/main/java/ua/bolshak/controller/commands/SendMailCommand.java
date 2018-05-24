package ua.bolshak.controller.commands;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Ticket;

class SendMailCommand {
    private static final Logger LOGGER = Logger.getLogger(SendMailCommand.class);
    private static final String MAIL = "bolshakmv@gmail.com";
    private static final String MAIL_PASSWORD = "1277joker1277";
    private static final String HOST_NAME = "smtp.gmail.com";
    private static final String SUBJECT = "Cruise Company";
    private static final String THANK_YOU = ", thank you for entrusting us with your rest and chose ";
    private static final String EXCURSION_INCLUDE = " Excursions are included in your ticket: \n";
    private static final String NEW_LINE = "\n";

    void sendEmail(Ticket ticket){
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
