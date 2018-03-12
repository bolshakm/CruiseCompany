package ua.bolshak.model.entity;

import ua.bolshak.model.service.CruiseService;

import java.util.List;
import java.util.Objects;

public class CruiseStatus {
    private int id;
    private String name;
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

    public List<Cruise> getCruises() {
        return CruiseService.findAllByStatus(this);
    }

    public void setCruises(List<Cruise> cruises) {
        this.cruises = cruises;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CruiseStatus that = (CruiseStatus) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CruiseStatus{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
