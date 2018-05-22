package ua.bolshak.util;

public enum Page {
    CRUISE("/jsp/cruise.jsp"), CRUISE_CARD("/jsp/cruiseCard.jsp"), ERROR("/jsp/error.jsp"), HEADER("/jsp/header.jsp"),
    LOGIN("/jsp/login.jsp"), PORT("/jsp/port.jsp"), REGISTRATION("/jsp/registration.jsp"),
    ROUTE("/jsp/route.jsp"), ROUTE_CARD("/jsp/routeCard.jsp"), SHIP("/jsp/ship.jsp"),
    SHIP_ADMINISTRATOR("/jsp/shipAdministrator.jsp"), SHIP_CARD("/jsp/shipCard.jsp"), STUFF("/jsp/stuffPage.jsp"),
    TICKET_CARD("/jsp/ticketCard.jsp"), TICKETS("/jsp/tickets.jsp"), TICKET_TYPE_CARD("/jsp/ticketTypeCard.jsp"),
    USER_CARD("/jsp/userCard.jsp"), USERS("/jsp/users.jsp"), INDEX(("/index.jsp"));

    private String page;

    Page(String page){
        this.page=page;
    }

    public String getPage() {
        return page;
    }
}
