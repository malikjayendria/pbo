package com.vehicle.controller;

import com.vehicle.dto.UserDto;
import com.vehicle.model.User;
import com.vehicle.service.UserServiceImpl;
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
    private UserServiceImpl userService;

    @Autowired
    public DashboardController(VehicleService vehicleService, CarService carService, MotorcycleService motorcycleService, UserServiceImpl userService) {
        this.vehicleService = vehicleService;
        this.carService = carService;
        this.motorcycleService = motorcycleService;
        this.userService = userService;
    }

    @GetMapping
    public String dashboard(@SessionAttribute(name = "userId", required = false) Long userId, Model model) {
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
//        if (user.getRole().equals("ADMIN")) {
//            model.addAttribute("vehicles", vehicleService.getAllVehicles());
//        } else {
//            model.addAttribute("vehicles", vehicleService.getVehiclesByUserId(userId));
//        }

        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("userRole", user.getRole());
        model.addAttribute("carDto", new CarDto());
        model.addAttribute("motorcycleDto", new MotorcycleDto());
        return "dashboard";
    }

    @PostMapping("/car/add")
    public String tambahMobil(@ModelAttribute CarDto carDto,
                              @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/dashboard";
        }

        carService.saveCar(carDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/motorcycle/add")
    public String tambahMotor(@ModelAttribute MotorcycleDto motorcycleDto,
                              @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/dashboard";
        }

        motorcycleService.saveMotorcycle(motorcycleDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/car/update/{id}")
    public String perbaruiMobil(@PathVariable Long id,
                                @ModelAttribute CarDto carDto,
                                @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/dashboard";
        }

        carDto.setId(id);
        carService.updateCar(carDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/motorcycle/update/{id}")
    public String perbaruiMotor(@PathVariable Long id,
                                @ModelAttribute MotorcycleDto motorcycleDto,
                                @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/dashboard";
        }

        motorcycleDto.setId(id);
        motorcycleService.updateMotorcycle(motorcycleDto, userId);
        return "redirect:/dashboard";
    }

    @PostMapping("/vehicle/delete/{id}")
    public String hapusKendaraan(@PathVariable Long id,
                                 @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/dashboard";
        }

        vehicleService.deleteVehicle(id);
        return "redirect:/dashboard";
    }
}