package com.vehicle.dto;

public class MotorcycleDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String registrationNumber;
    private String chainType;
    private Boolean hasKickstand;

    // Getters
    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public Integer getYear() { return year; }
    public String getRegistrationNumber() { return registrationNumber; }
    public String getChainType() { return chainType; }
    public Boolean getHasKickstand() { return hasKickstand; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYear(Integer year) { this.year = year; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public void setChainType(String chainType) { this.chainType = chainType; }
    public void setHasKickstand(Boolean hasKickstand) { this.hasKickstand = hasKickstand; }
}