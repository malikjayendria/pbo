package com.vehicle.repository;

import com.vehicle.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByUserId(Long userId);
    List<Rent> findByVehicleIdAndIsActive(Long vehicleId, boolean isActive);
}