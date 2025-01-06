package com.vehicle.service;

import com.vehicle.model.Rent;
import com.vehicle.model.User;
import com.vehicle.model.Vehicle;
import com.vehicle.repository.RentRepository;
import com.vehicle.repository.UserRepository;
import com.vehicle.repository.VehicleRepository;
import com.vehicle.dto.RentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Rent rentVehicle(RentDto rentDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User  not found"));

        Vehicle vehicle = vehicleRepository.findById(rentDto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        List<Rent> activeRents = rentRepository.findByVehicleIdAndIsActive(vehicle.getId(), true);
        if (!activeRents.isEmpty()) {
            throw new RuntimeException("Vehicle is already rented.");
        }

        Rent rent = new Rent();
        rent.setUser (user);
        rent.setVehicle(vehicle);
        rent.setRentDate(rentDto.getRentDate());
        rent.setReturnDate(rentDto.getReturnDate());
        rent.setActive(true);
        return rentRepository.save(rent);
    }

    @Override
    public List<Rent> getRentsByUserId(Long userId) {
        return rentRepository.findByUserId(userId);
    }

    @Override
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }
}