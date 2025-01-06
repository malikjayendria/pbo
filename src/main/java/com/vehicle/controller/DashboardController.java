package com.vehicle.controller;

import com.vehicle.dto.RentDto;
import com.vehicle.model.Rent;
import com.vehicle.model.User;
import com.vehicle.service.*;
import com.vehicle.dto.CarDto;
import com.vehicle.dto.MotorcycleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CarService carService;

    @Autowired
    private RentService rentService;

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

        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("userRole", user.getRole());
        model.addAttribute("carDto", new CarDto());
        model.addAttribute("motorcycleDto", new MotorcycleDto());

        List<Rent> rents = rentService.getRentsByUserId(userId);
        model.addAttribute("rents", rents);

        if (user.getRole().equals("ADMIN")) {
            List<Rent> allRents = rentService.getAllRents();
            model.addAttribute("allRents", allRents);
        }

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

    @PostMapping("/vehicle/rent")
    public String rentVehicle(@ModelAttribute RentDto rentDto,
                              @SessionAttribute(name = "userId", required = false) Long userId,
                              RedirectAttributes redirectAttributes) {
        if (userId == null) {
            return "redirect:/login";
        }

        try {
            Rent rent = rentService.rentVehicle(rentDto, userId);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/dashboard";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/rental/delete/{id}")
    public String deleteRental(@PathVariable Long id,
                               @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/dashboard";
        }

        rentService.deleteRental(id);
        return "redirect:/dashboard";
    }
}