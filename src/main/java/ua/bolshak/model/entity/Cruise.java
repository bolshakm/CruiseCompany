package ua.bolshak.model.entity;

import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.TicketService;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Cruise {

    private int id;
    private String name;
    private Date from;
    private Date to;
    private double money;
    private Ship ship;
    private CruiseStatus status;
    private List<Ticket> tickets;
    private List<Port> ports;

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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Ship getShip() {
        return ShipService.findByCruise(this);
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public CruiseStatus getStatus() {
        return CruiseStatusService.findByCruise(this);
    }

    public void setStatus(CruiseStatus status) {
        this.status = status;
    }

    public List<Ticket> getTickets() {
        return TicketService.findAllByCruise(this);
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Port> getPorts() {
        return PortService.findAllByCruise(this);
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cruise cruise = (Cruise) o;
        return Double.compare(cruise.money, money) == 0 &&
                Objects.equals(name, cruise.name) &&
                Objects.equals(from, cruise.from) &&
                Objects.equals(to, cruise.to);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, from, to, money);
    }

    @Override
    public String toString() {
        return "Cruise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", money=" + money +
                '}';
    }
}
