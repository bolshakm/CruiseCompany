package ua.bolshak.model.entity;

import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ShipTypeService;

import java.util.List;
import java.util.Objects;

public class Ship {
    private int id;
    private String name;
    private String number;
    private int numberOfSeats;
    private double pricePerSeat;
    private ShipType type;
    private List<Bonus> bonuses;
    private List<Cruise> cruises;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public ShipType getType() {
        return ShipTypeService.findByShip(this);
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public List<Bonus> getBonuses() {
        return BonusService.findAllByShip(this);
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public List<Cruise> getCruises() {
        return CruiseService.findAllByShip(this);
    }

    public void setCruises(List<Cruise> cruises) {
        this.cruises = cruises;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return numberOfSeats == ship.numberOfSeats &&
                Double.compare(ship.pricePerSeat, pricePerSeat) == 0 &&
                Objects.equals(name, ship.name) &&
                Objects.equals(number, ship.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, number, numberOfSeats, pricePerSeat);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", pricePerSeat=" + pricePerSeat +
                '}';
    }
}
