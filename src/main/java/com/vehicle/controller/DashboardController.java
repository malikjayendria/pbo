package com.vehicle.controller;

import com.vehicle.service.VehicleService;
import com.vehicle.service.CarService;
import com.vehicle.service.MotorcycleService;
import com.vehicle.dto.CarDto;
import com.vehicle.dto.MotorcycleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CarService carService;

    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping
    public String dashboard(Model model) {
        // TODO: Get actual logged in user ID
        Long userId = 1L; // Temporary, replace with actual user session
        model.addAttribute("vehicles", vehicleService.getVehiclesByUserId(userId));
        model.addAttribute("carDto", new CarDto());
        model.addAttribute("motorcycleDto", new MotorcycleDto());
        return "dashboard";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute CarDto carDto) {
        Long userId = 1L; // Temporary, replace with actual user session
        carService.saveCar(carDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/motorcycle/add")
    public String addMotorcycle(@ModelAttribute MotorcycleDto motorcycleDto) {
        Long userId = 1L; // Temporary, replace with actual user session
        motorcycleService.saveMotorcycle(motorcycleDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/car/update")
    public String updateCar(@ModelAttribute CarDto carDto) {
        Long userId = 1L; // Temporary, replace with actual user session
        carService.updateCar(carDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/motorcycle/update")
    public String updateMotorcycle(@ModelAttribute MotorcycleDto motorcycleDto) {
        Long userId = 1L; // Temporary, replace with actual user session
        motorcycleService.updateMotorcycle(motorcycleDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/vehicle/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/dashboard";
    }
}