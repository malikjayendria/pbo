package com.vehicle.service;

import com.vehicle.model.User;
import com.vehicle.dto.UserDto;

public interface UserService {
    User registerUser(UserDto userDto);
    User loginUser(String username, String password);
}
