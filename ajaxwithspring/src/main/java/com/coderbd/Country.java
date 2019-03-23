package com.coderbd;

import java.util.Objects;

public class Country {
    private Long id;
    private String country;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public Country(Long id, String country) {
        this.id = id;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country1 = (Country) o;
        return Objects.equals(getId(), country1.getId()) &&
                Objects.equals(getCountry(), country1.getCountry());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCountry());
    }
}
