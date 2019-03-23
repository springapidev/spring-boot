package com.coderbd;

import java.util.Objects;

public class City {
    private Long id;
    private String city;

    public City() {
    }

    public City(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
