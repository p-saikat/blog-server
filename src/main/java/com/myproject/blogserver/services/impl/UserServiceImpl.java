package com.myproject.blogserver.services.impl;

import com.myproject.blogserver.entities.User;
import com.myproject.blogserver.exceptions.ResourceNotFoundException;
import com.myproject.blogserver.payloads.UserDTO;
import com.myproject.blogserver.repositories.UserRepository;
import com.myproject.blogserver.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {

        User user = this.dtoToUser(userDto);
        User saveUser = this.userRepo.save(user);
        return this.userToDto(saveUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = userRepo.save(user);
        return this.userToDto(updateUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
       List<User> users = userRepo.findAll();

       List<UserDTO> usersDto = users.stream()
               .map(user -> this.userToDto(user)).collect(Collectors.toList());
       return usersDto;
    }

    @Override
    public void deleteUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id", userId));

        userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDTO userToDto(User user) {
        UserDTO userDto = this.modelMapper.map(user, UserDTO.class);
        return userDto;
    }
}
