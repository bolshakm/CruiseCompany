package ua.bolshak.model.entity;

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
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", cruise=" + cruise +
                ", ticketType=" + ticketType +
                ", bonuses=" + bonuses +
                '}';
    }
}
