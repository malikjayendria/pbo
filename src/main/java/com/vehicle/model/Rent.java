package com.vehicle.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

    @Column(nullable = false)
    private boolean isActive; // Menandakan apakah rental masih aktif

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser () { return user; }
    public void setUser (User user) { this.user = user; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public LocalDateTime getRentDate() { return rentDate; }
    public void setRentDate(LocalDateTime rentDate) { this.rentDate = rentDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active; // Pastikan untuk menggunakan nama variabel yang benar
    }
}