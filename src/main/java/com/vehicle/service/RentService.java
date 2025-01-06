package com.vehicle.service;

import com.vehicle.model.Rent;
import com.vehicle.dto.RentDto;

import java.util.List;

public interface RentService {
    Rent rentVehicle(RentDto rentDto, Long userId);
    List<Rent> getRentsByUserId(Long userId);
    List<Rent> getAllRents();
    void deleteRental(Long id);
}