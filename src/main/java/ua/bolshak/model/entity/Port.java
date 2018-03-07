package ua.bolshak.model.entity;

import java.util.List;
import java.util.Objects;

public class Port {
    private int id;
    private String name;
    private String city;
    private String country;
    private List<Cruise> cruises;
    private List<Excursion> excursions;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Cruise> getCruises() {
        return cruises;
    }

    public void setCruises(List<Cruise> cruises) {
        this.cruises = cruises;
    }

    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(name, port.name) &&
                Objects.equals(city, port.city) &&
                Objects.equals(country, port.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, city, country);
    }

    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", cruises=" + cruises +
                ", excursions=" + excursions +
                '}';
    }
}
