package ua.bolshak.model.entity;

import java.util.List;
import java.util.Objects;

public class Excursion {
    private int id;
    private String name;
    private double price;
    private Port port;
    private List<Ticket> tickets;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excursion excursion = (Excursion) o;
        return Double.compare(excursion.price, price) == 0 &&
                Objects.equals(name, excursion.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Excursion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

