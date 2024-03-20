package com.myproject.blogserver.services;

import com.myproject.blogserver.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer userId);

    UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUserById(Integer userId);
}
