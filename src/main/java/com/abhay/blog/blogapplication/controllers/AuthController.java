package com.abhay.blog.blogapplication.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.blog.blogapplication.entities.User;
import com.abhay.blog.blogapplication.exceptions.ApiException;
import com.abhay.blog.blogapplication.payloads.JwtAuthRequest;
import com.abhay.blog.blogapplication.payloads.JwtAuthResponse;
import com.abhay.blog.blogapplication.payloads.UserDto;
import com.abhay.blog.blogapplication.security.JwtTokenHelper;
import com.abhay.blog.blogapplication.services.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken( @RequestBody JwtAuthRequest request){
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        UserDto userDto = this.userService.getUserByEmail(request.getUsername());
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setId(userDto.getId());

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username , String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username , password);
        try{
        this.authenticationManager.authenticate(authenticationToken);
        }catch(BadCredentialsException e){
            System.out.println("Invalid Details");
            throw new ApiException("Invalid username or pass");
        }
    }

    // register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

        UserDto registeredUser =  this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }

    // register new Admin
    @PostMapping("/registerAdmin")
    public ResponseEntity<UserDto> registerAdminUser(@RequestBody UserDto userDto){

        UserDto registeredUser =  this.userService.registerNewAdminUser(userDto);
        return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }
}
