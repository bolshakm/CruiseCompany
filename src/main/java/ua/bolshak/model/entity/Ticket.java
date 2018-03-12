package ua.bolshak.model.entity;

import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.TicketTypeService;
import ua.bolshak.model.service.UserService;

import java.util.List;
import java.util.Objects;

public class Ticket {
    private int id;
    private User user;
    private Cruise cruise;
    private TicketType ticketType;
    private List<Bonus> bonuses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return UserService.findByTicket(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cruise getCruise() {
        return CruiseService.findByTicket(this);
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    public TicketType getTicketType() {
        return TicketTypeService.findByTicket(this);
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public List<Bonus> getBonuses() {
        return BonusService.findAllByTicket(this);
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(user, ticket.user) &&
                Objects.equals(cruise, ticket.cruise) &&
                Objects.equals(ticketType, ticket.ticketType) &&
                Objects.equals(bonuses, ticket.bonuses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, cruise, ticketType, bonuses);
    }
}
