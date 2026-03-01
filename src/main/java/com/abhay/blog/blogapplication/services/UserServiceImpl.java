package com.abhay.blog.blogapplication.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhay.blog.blogapplication.config.AppConstants;
import com.abhay.blog.blogapplication.entities.Role;
import com.abhay.blog.blogapplication.entities.User;
import com.abhay.blog.blogapplication.payloads.UserDto;
import com.abhay.blog.blogapplication.repositeries.UserRepo;
import com.abhay.blog.blogapplication.repositeries.RoleRepo;
import com.abhay.blog.blogapplication.exceptions.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAbout(userDto.getAbout());
        User updateSaved = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updateSaved);
        return userDto1;
        }

    @Override
    public UserDto getUserById(Integer userId) {
    
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        UserDto user1 = this.userToDto(user);
        return user1;
    }

    @Override
    public List<UserDto> getAllUsers() {
    List<User> users = this.userRepo.findAll();
    List<UserDto> userDtos = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
    return userDtos;

        }

    @Override
    public void deleteUser(Integer userId) {
    User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    this.userRepo.delete(user);
    
    }

    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto userToDto(User user){
            UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
        
        }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user =this.modelMapper.map(userDto, User.class);

        // encoded pass
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = this.roleRepo.findByName("USER").get();

        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser, UserDto.class);
    }

    public UserDto registerNewAdminUser(UserDto userDto) {
        User user =this.modelMapper.map(userDto, User.class);

        // encoded pass
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = this.roleRepo.findByName("ADMIN").get();

        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = this.userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("user", "email", email));
        return this.modelMapper.map(user, UserDto.class);
    }

}
