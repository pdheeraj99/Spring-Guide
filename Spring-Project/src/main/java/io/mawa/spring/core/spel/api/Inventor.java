package io.mawa.spring.core.spel.api;

import java.util.Date;

public class Inventor {

    private String name;
    private String nationality;
    private Date birthdate;
    private PlaceOfBirth placeOfBirth;
    private String[] inventions;

    public Inventor(String name, String nationality, Date birthdate, PlaceOfBirth placeOfBirth, String... inventions) {
        this.name = name;
        this.nationality = nationality;
        this.birthdate = birthdate;
        this.placeOfBirth = placeOfBirth;
        this.inventions = inventions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public PlaceOfBirth getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(PlaceOfBirth placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String[] getInventions() {
        return inventions;
    }

    public void setInventions(String[] inventions) {
        this.inventions = inventions;
    }
}
