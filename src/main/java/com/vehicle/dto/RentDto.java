package com.vehicle.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentDto {
    private Long vehicleId;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public LocalDateTime getRentDate() { return rentDate; }
    public void setRentDate(LocalDateTime rentDate) { this.rentDate = rentDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public String getFormattedRentDate() {
        return rentDate != null ? rentDate.format(formatter) : "";
    }

    public String getFormattedReturnDate() {
        return returnDate != null ? returnDate.format(formatter) : "";
    }
}