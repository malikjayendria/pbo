package com.vehicle.service;

import com.vehicle.model.Car;
import com.vehicle.dto.CarDto;
import com.vehicle.repository.CarRepository;
import com.vehicle.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserServiceImpl userService;

    public Car saveCar(CarDto carDto, Long userId) {
        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setRegistrationNumber(carDto.getRegistrationNumber());
        car.setNumberOfDoors(carDto.getNumberOfDoors());
        car.setTransmissionType(carDto.getTransmissionType());
        car.setHasAirbag(carDto.getHasAirbag());
        car.setUser(userService.getUserById(userId));
        return carRepository.save(car);
    }

    public Car updateCar(CarDto carDto, Long userId) {
        Car car = carRepository.findById(carDto.getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setRegistrationNumber(carDto.getRegistrationNumber());
        car.setNumberOfDoors(carDto.getNumberOfDoors());
        car.setTransmissionType(carDto.getTransmissionType());
        car.setHasAirbag(carDto.getHasAirbag());

        return carRepository.save(car);
    }
}