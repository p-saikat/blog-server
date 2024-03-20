package com.myproject.blogserver.controllers;

import com.myproject.blogserver.payloads.ApiResponse;
import com.myproject.blogserver.payloads.UserDTO;
import com.myproject.blogserver.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO createUser(@Valid @RequestBody UserDTO userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO updateUser(@RequestBody UserDTO userDto, @PathVariable Integer userId) {
        return userService.updateUser(userDto, userId);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    /* public List<UserDTO> getAllUser() {
        return userService.getAllUsers();
    } */

    /* public Map<String, Object> getAllUser() {
        List<UserDTO> users = userService.getAllUsers();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Records fetched successfully.");
        response.put("payload", users);
        return response;
    } */

    public ApiResponse getAllUser() {
        List<UserDTO> users = userService.getAllUsers();
        return new ApiResponse("Records fetched successfully.", true, users);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Record deleted successfully.");
        return response;
    }


}
