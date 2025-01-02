package com.vehicle.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vehicles")
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private Integer year;
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "vehicle_type")
    private String vehicleType;

    // Getters
    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public Integer getYear() { return year; }
    public String getRegistrationNumber() { return registrationNumber; }
    public User getUser() { return user; }
    public String getVehicleType() { return vehicleType; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYear(Integer year) { this.year = year; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public void setUser(User user) { this.user = user; }

    @PrePersist
    public void setVehicleType() {
        if (this instanceof Car) {
            this.vehicleType = "CAR";
        } else if (this instanceof Motorcycle) {
            this.vehicleType = "MOTORCYCLE";
        }
    }
}