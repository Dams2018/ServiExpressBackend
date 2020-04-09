package com.porta.porta.controller;

import com.porta.porta.exception.AppException;
import com.porta.porta.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porta.porta.entity.Role;
import com.porta.porta.entity.RoleName;
import com.porta.porta.entity.User;
import com.porta.porta.payload.ApiResponse;
import com.porta.porta.payload.ChangeRequest;
import com.porta.porta.payload.JwtAuthenticationResponse;
import com.porta.porta.payload.LoginRequest;
import com.porta.porta.payload.SignUpRequest;
import com.porta.porta.repository.RoleRepository;
import com.porta.porta.repository.UserRepository;
import com.porta.porta.security.JwtTokenProvider;
import com.porta.porta.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.jws.soap.SOAPBinding.Use;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        EmailService emailService;

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        UserRepository userRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        JwtTokenProvider tokenProvider;

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.generateToken(authentication);
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }

        // @PostMapping("/check2")
        // public ResponseEntity<?> check(@Valid @RequestBody ChangeRequest changeRequest) {
        //         Boolean isAvailable = userRepository.existsByUsername(changeRequest.getUsername());
        //         Optional<User> list = userRepository.findByUsername(changeRequest.getUsername());

        //         list.toString();
        //         try {
        //                 System.out.println("mapper -> " + new ObjectMapper().writeValueAsString(list));
        //         } catch (JsonProcessingException e) {
        //                 // TODO Auto-generated catch block
        //                 e.printStackTrace();
        //         }
        //         return ResponseEntity.ok(isAvailable);
        // }


        @PutMapping("/changepassword/{id}")
        public ResponseEntity < User > update(@PathVariable(value = "id") Long employeeId,
            @Valid @RequestBody ChangeRequest changeRequest) throws ResourceNotFoundException {
            User user = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

                user.setPassword(passwordEncoder.encode(changeRequest.getPassword()));
            final User updatedEmployee = userRepository.save(user);
            return ResponseEntity.ok(updatedEmployee);
        }

        

        @PutMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                                        HttpStatus.BAD_REQUEST);
                }

                // Creating user's account
                User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                                signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new AppException("User Role not set."));

                user.setRoles(Collections.singleton(userRole));
                user.setActive(false);

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();
                emailService.emailSend(signUpRequest.getEmail(), signUpRequest.getName(), signUpRequest.getUsername(),
                                signUpRequest.getPassword());
                return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
        }

        @PostMapping("/signupwork")
        public ResponseEntity<?> registerWork(@Valid @RequestBody SignUpRequest signUpRequest) {

                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                                        HttpStatus.BAD_REQUEST);
                }

                // Creating user's account
                User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                                signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                if (signUpRequest.getRole().equals("1")) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                        .orElseThrow(() -> new AppException("User Role not set."));

                        user.setRoles(Collections.singleton(userRole));
                }
                if (signUpRequest.getRole().equals("3")) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYE)
                                        .orElseThrow(() -> new AppException("User Role not set."));

                        user.setRoles(Collections.singleton(userRole));

                }
                if (signUpRequest.getRole().equals("4")) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_PROVIDER)
                                        .orElseThrow(() -> new AppException("User Role not set."));

                        user.setRoles(Collections.singleton(userRole));
                } else {

                }

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();

                return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
        }
}