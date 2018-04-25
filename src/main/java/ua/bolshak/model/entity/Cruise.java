package ua.bolshak.model.entity;


import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Cruise {

    private int id;
    private String name;
    private Date from;
    private Date to;
    private Ship ship;
    private Route route;
    private CruiseStatus status;
    private List<Ticket> tickets;
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public CruiseStatus getStatus() {
        return status;
    }

    public void setStatus(CruiseStatus status) {
        this.status = status;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cruise cruise = (Cruise) o;
        return Objects.equals(name, cruise.name) &&
                Objects.equals(from, cruise.from) &&
                Objects.equals(to, cruise.to);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, from, to);
    }

    @Override
    public String toString() {
        return "Cruise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
