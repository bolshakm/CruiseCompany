package ua.bolshak.model.entity;

import ua.bolshak.model.service.ShipService;
import ua.bolshak.model.service.TicketService;

import java.util.List;
import java.util.Objects;

public class Bonus {

    private int id;
    private String name;
    private List<Ticket> tickets;
    private List<Ship> ships;

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

    public List<Ticket> getTickets() {
        return TicketService.findAllByBonus(this);
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ship> getShips() {
        return ShipService.findAllByBonus(this);
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return Objects.equals(name, bonus.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
