package com.alexfanning.artwaves.venueitems;

/**
 * Created by alex.fanning on 24/07/2017.
 */

public class Venue {

    private String name;
    private String location;
    private String description;
    private String[] coordinates;

    public Venue(){

    }
    public Venue(String name, String location, String description, String[] coordinates) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }




}
