package com.vehicle.service;

import com.vehicle.model.Motorcycle;
import com.vehicle.dto.MotorcycleDto;
import com.vehicle.repository.MotorcycleRepository;
import com.vehicle.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorcycleService {
    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserServiceImpl userService;

    public Motorcycle saveMotorcycle(MotorcycleDto motoDto, Long userId) {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setBrand(motoDto.getBrand());
        motorcycle.setModel(motoDto.getModel());
        motorcycle.setYear(motoDto.getYear());
        motorcycle.setRegistrationNumber(motoDto.getRegistrationNumber());
        motorcycle.setChainType(motoDto.getChainType());
        motorcycle.setHasKickstand(motoDto.getHasKickstand());
        motorcycle.setUser(userService.getUserById(userId));
        return motorcycleRepository.save(motorcycle);
    }

    public Motorcycle updateMotorcycle(MotorcycleDto motoDto, Long userId) {
        Motorcycle motorcycle = motorcycleRepository.findById(motoDto.getId())
                .orElseThrow(() -> new RuntimeException("Motorcycle not found"));

        motorcycle.setBrand(motoDto.getBrand());
        motorcycle.setModel(motoDto.getModel());
        motorcycle.setYear(motoDto.getYear());
        motorcycle.setRegistrationNumber(motoDto.getRegistrationNumber());
        motorcycle.setChainType(motoDto.getChainType());
        motorcycle.setHasKickstand(motoDto.getHasKickstand());

        return motorcycleRepository.save(motorcycle);
    }
}