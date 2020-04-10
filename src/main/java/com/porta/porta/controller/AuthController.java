package com.porta.porta.controller;

import com.porta.porta.exception.AppException;
import com.porta.porta.exception.ResourceNotFoundException;

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
import com.porta.porta.util.Util;
import com.porta.porta.vo.MensajeVO;
import com.porta.porta.vo.ResultadoVO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

import javax.validation.Valid;
import java.net.URI;
import java.security.Key;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        private final Logger log = LoggerFactory.getLogger(this.getClass());
	ResultadoVO salida = new ResultadoVO();
	MensajeVO mensajeError =new MensajeVO();
	String[] mensajes = new String[3];
        Key key = new AesKey(ByteUtil.randomBytes(16));

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.generateToken(authentication);
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        }

        @PutMapping("/requestpass/{username}")
        public ResponseEntity<?> update(@PathVariable(value = "username") String username) throws JoseException {

                try {
                        User user = userRepository.findByUsername(username)
                                        .orElseThrow(() -> new IllegalStateException("Usuario no existe."));

                        JsonWebEncryption jwe = new JsonWebEncryption();
                        jwe.setPayload(Long.toString(user.getId()));
                        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
                        jwe.setEncryptionMethodHeaderParameter(
                                        ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
                        jwe.setKey(key);
                        String serializedJwe = jwe.getCompactSerialization();
                        System.out.println("Serialized Encrypted JWE: " + serializedJwe);

                        return ResponseEntity.ok(serializedJwe);
                } catch (Exception e) {
                        log.error("HA OCURRIDO UN ERROR " + e.getMessage());
                        String[] timestampError = Util.getCurrentTimeStamp().split(";");
                        mensajes = Util.Codigos.MALPARAMETROS.split(";");    
                        mensajeError = new MensajeVO(timestampError[0], timestampError[1], e.getMessage(),mensajes[0]);
                        salida.setPeticion(mensajeError);
                        return new ResponseEntity<ResultadoVO>(salida, HttpStatus.CONFLICT);
                }

        }

        @PutMapping("/changepassword/{id}")
        public ResponseEntity<?> update(@PathVariable(value = "id") String userId,
                        @Valid @RequestBody ChangeRequest changeRequest)
                        throws ResourceNotFoundException, JoseException {

                try {
                        JsonWebEncryption jwe = new JsonWebEncryption();
                        jwe = new JsonWebEncryption();
                        jwe.setAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST,
                                        KeyManagementAlgorithmIdentifiers.A128KW));
                        jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST,
                                        ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256));
                        jwe.setKey(key);
                        jwe.setCompactSerialization(userId);
                        Long id = Long.parseLong(jwe.getPayload());

                        User user = userRepository.findById(id)
                                        .orElseThrow(() -> new IllegalStateException("IdUsuario no existe."));
                        user.setPassword(passwordEncoder.encode(changeRequest.getPassword()));

                        mensajes = Util.Codigos.PASSWORDOK.split(";");
                        String[] timestamp = Util.getCurrentTimeStamp().split(";");
                        MensajeVO mensaje = new MensajeVO(timestamp[0], timestamp[1], mensajes[1], mensajes[0]);
                        userRepository.save(user);
                        salida.setPeticion(mensaje);
                } catch (Exception e) {
                        log.error("HA OCURRIDO UN ERROR " + e.getMessage());
                        String[] timestampError = Util.getCurrentTimeStamp().split(";");
                        mensajes = Util.Codigos.MALPARAMETROS.split(";");    
                        mensajeError = new MensajeVO(timestampError[0], timestampError[1], e.getMessage(),mensajes[0]);
                        salida.setPeticion(mensajeError);
                        return new ResponseEntity<ResultadoVO>(salida, HttpStatus.CONFLICT);
                        
                }

                return new ResponseEntity<ResultadoVO>(salida, HttpStatus.OK);
                // http://127.0.0.1:8090/api/auth/changepassword/1
        }

        @PutMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return new ResponseEntity(new ApiResponse(false, "¡Este nombre de usuario ya existe!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return new ResponseEntity(
                                        new ApiResponse(false, "¡Dirección de correo electrónico ya está en uso!"),
                                        HttpStatus.BAD_REQUEST);
                }

                // Creating user's account
                User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                                signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new AppException("Rol de usuario no establecido"));

                user.setRoles(Collections.singleton(userRole));
                user.setActive(true);

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();
                emailService.emailSend(signUpRequest.getEmail(), signUpRequest.getName(), signUpRequest.getUsername(),
                                signUpRequest.getPassword());
                return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado exitosamente"));
        }

        @PostMapping("/signupwork")
        public ResponseEntity<?> registerWork(@Valid @RequestBody SignUpRequest signUpRequest) {

                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        return new ResponseEntity(new ApiResponse(false, "¡Este nombre de usuario ya existe!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                        return new ResponseEntity(
                                        new ApiResponse(false, "¡Dirección de correo electrónico ya está en uso!"),
                                        HttpStatus.BAD_REQUEST);
                }

                // Creating user's account
                User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                                signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                if (signUpRequest.getRole().equals("1")) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                        .orElseThrow(() -> new AppException("Rol de admin no establecido"));

                        user.setRoles(Collections.singleton(userRole));
                }
                if (signUpRequest.getRole().equals("3")) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYE)
                                        .orElseThrow(() -> new AppException("Rol de empleado no establecido"));

                        user.setRoles(Collections.singleton(userRole));

                }
                if (signUpRequest.getRole().equals("4")) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_PROVIDER)
                                        .orElseThrow(() -> new AppException("Rol de proveedor no establecido"));

                        user.setRoles(Collections.singleton(userRole));
                } else {

                }

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();

                return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado exitosamente"));
        }

        // @PutMapping("/requestpass/{id}")
        // public ResponseEntity<?> update(@PathVariable(value = "id") Long userId)
        // throws JoseException {

        // JsonWebEncryption jwe = new JsonWebEncryption();
        // jwe.setPayload(Long.toString(userId));
        // jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        // jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        // jwe.setKey(key);
        // String serializedJwe = jwe.getCompactSerialization();
        // System.out.println("Serialized Encrypted JWE: " + serializedJwe);

        // return ResponseEntity.ok(serializedJwe);
        // }
}