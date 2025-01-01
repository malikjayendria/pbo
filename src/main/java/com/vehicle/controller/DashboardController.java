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
    public String dashboard(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        if (userId == null) {
            return "redirect:/login"; // Redirect ke login jika userId tidak ada
        }
        model.addAttribute("vehicles", vehicleService.getVehiclesByUserId(userId));
        model.addAttribute("carDto", new CarDto());
        model.addAttribute("motorcycleDto", new MotorcycleDto());
        return "dashboard";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute CarDto carDto, @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login"; // Redirect ke login jika userId tidak ada
        }
        carService.saveCar(carDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/motorcycle/add")
    public String addMotorcycle(@ModelAttribute MotorcycleDto motorcycleDto, @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login"; // Redirect ke login jika userId tidak ada
        }
        motorcycleService.saveMotorcycle(motorcycleDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/car/update")
    public String updateCar(@ModelAttribute CarDto carDto, @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login"; // Redirect ke login jika userId tidak ada
        }
        carService.updateCar(carDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/motorcycle/update")
    public String updateMotorcycle(@ModelAttribute MotorcycleDto motorcycleDto, @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login"; // Redirect ke login jika userId tidak ada
        }
        motorcycleService.updateMotorcycle(motorcycleDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/vehicle/delete/{id}")
    public String deleteVehicle(@PathVariable Long id, @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login"; // Redirect ke login jika userId tidak ada
        }
        vehicleService.deleteVehicle(id);
        return "redirect:/dashboard";
    }
}