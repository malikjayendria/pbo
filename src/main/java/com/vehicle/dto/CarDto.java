package com.vehicle.dto;

public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String registrationNumber;
    private Integer numberOfDoors;
    private String transmissionType;
    private Boolean hasAirbag;

    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public Integer getYear() { return year; }
    public String getRegistrationNumber() { return registrationNumber; }
    public Integer getNumberOfDoors() { return numberOfDoors; }
    public String getTransmissionType() { return transmissionType; }
    public Boolean getHasAirbag() { return hasAirbag; }

    public void setId(Long id) { this.id = id; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYear(Integer year) { this.year = year; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public void setNumberOfDoors(Integer numberOfDoors) { this.numberOfDoors = numberOfDoors; }
    public void setTransmissionType(String transmissionType) { this.transmissionType = transmissionType; }
    public void setHasAirbag(Boolean hasAirbag) { this.hasAirbag = hasAirbag; }
}