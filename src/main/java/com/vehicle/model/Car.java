package com.vehicle.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
@PrimaryKeyJoinColumn(name = "vehicle_id")
public class Car extends Vehicle {
    private Integer numberOfDoors;
    private String transmissionType;
    private Boolean hasAirbag;

    // Getters
    public Integer getNumberOfDoors() { return numberOfDoors; }
    public String getTransmissionType() { return transmissionType; }
    public Boolean getHasAirbag() { return hasAirbag; }

    // Setters
    public void setNumberOfDoors(Integer numberOfDoors) { this.numberOfDoors = numberOfDoors; }
    public void setTransmissionType(String transmissionType) { this.transmissionType = transmissionType; }
    public void setHasAirbag(Boolean hasAirbag) { this.hasAirbag = hasAirbag; }

}