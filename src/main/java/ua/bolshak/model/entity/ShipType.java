package ua.bolshak.model.entity;

import ua.bolshak.model.service.ShipService;

import java.util.List;
import java.util.Objects;

public class ShipType {
    private int id;
    private String name;
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

    public List<Ship> getShips() {
        return ShipService.findAllByShipType(this);
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipType shipType = (ShipType) o;
        return Objects.equals(name, shipType.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ShipType{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
