package com.serviexpress.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serviexpress.apirest.entity.Role;
import com.serviexpress.apirest.entity.RoleName;
import com.serviexpress.apirest.entity.User;
import com.serviexpress.apirest.entity.UserRole;
import com.serviexpress.apirest.exception.AppException;
import com.serviexpress.apirest.exception.ResourceNotFoundException;
import com.serviexpress.apirest.payload.ApiResponse;
import com.serviexpress.apirest.payload.ChangeRequest;
import com.serviexpress.apirest.payload.JwtAuthenticationResponse;
import com.serviexpress.apirest.payload.LoginRequest;
import com.serviexpress.apirest.payload.SignUpRequest;
import com.serviexpress.apirest.repository.RoleRepository;
import com.serviexpress.apirest.repository.UserRepository;
import com.serviexpress.apirest.repository.UserRoleRepository;
import com.serviexpress.apirest.security.JwtTokenProvider;
import com.serviexpress.apirest.service.EmailService;
import com.serviexpress.apirest.util.Util;
import com.serviexpress.apirest.vo.MensajeVO;
import com.serviexpress.apirest.vo.ResultadoVO;

import org.jose4j.json.internal.json_simple.JSONObject;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
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
        UserRoleRepository userRoleRepository;

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        JwtTokenProvider tokenProvider;

        private final Logger log = LoggerFactory.getLogger(this.getClass());
        ResultadoVO salida = new ResultadoVO();
        MensajeVO mensajeError = new MensajeVO();
        String[] mensajes = new String[3];
        Key key = new AesKey(ByteUtil.randomBytes(16));

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,Errors errors) {

                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                                                loginRequest.getPassword()));
                                               
                                                
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.generateToken(authentication);
                User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new IllegalStateException("UserName existe."));
                UserRole userRole= userRoleRepository.findById(user.getId()).orElseThrow(() -> new IllegalStateException("UserId no existe."));
                Role role= roleRepository.findById(userRole.getRoleid()).orElseThrow(() -> new IllegalStateException("RoleId no existe."));
    
			JSONObject lista = new JSONObject();
			lista.put("username", authentication.getName());
			lista.put("Avtivo", user.isActive());
			lista.put("iduser", user.getId());
			lista.put("name", user.getName());
			lista.put("idrole", role.getId());
			lista.put("rolename", role.getName());
                        lista.put("accessToken", jwt);
                        lista.put("tokenType", "Bearer");
	
		return new ResponseEntity<Object>(lista, HttpStatus.OK);
                // if (!errors.hasErrors()) {
                //         log.warn("DETAIL "+errors.getAllErrors().toString()+ errors.hasErrors());
                // } 
                //return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
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
                        String[] timestampError = Util.getCurrentTimeStamp().split(";");
                        mensajes = Util.Codigos.MALPARAMETROS.split(";");
                        mensajeError = new MensajeVO(timestampError[0], timestampError[1], serializedJwe, "OK DEKU");
                        salida.setPeticion(mensajeError);
                        System.out.println("Serialized Encrypted JWE: " + serializedJwe);
                        return new ResponseEntity<ResultadoVO>(salida, HttpStatus.ACCEPTED);

                        // return ResponseEntity.ok(serializedJwe);
                } catch (Exception e) {
                        log.error("HA OCURRIDO UN ERROR " + e.getMessage());
                        String[] timestampError = Util.getCurrentTimeStamp().split(";");
                        mensajes = Util.Codigos.MALPARAMETROS.split(";");
                        mensajeError = new MensajeVO(timestampError[0], timestampError[1], e.getMessage(), mensajes[0]);
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
                        mensajeError = new MensajeVO(timestampError[0], timestampError[1], e.getMessage(), mensajes[0]);
                        salida.setPeticion(mensajeError);
                        return new ResponseEntity<ResultadoVO>(salida, HttpStatus.CONFLICT);

                }

                return new ResponseEntity<ResultadoVO>(salida, HttpStatus.OK);
                // http://127.0.0.1:8090/api/auth/changepassword/1
        }

        @PutMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
                if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                        log.error("¡Este nombre de usuario ya existe!");
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

                if (signUpRequest.getRole().equals(RoleName.ROLE_CLIENT.getId())) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
                                        .orElseThrow(() -> new AppException("Rol de usuario no establecido"));
                        user.setRoles(Collections.singleton(userRole));
                        log.info("Usuario " + signUpRequest.getUsername() + " " + RoleName.ROLE_CLIENT.getName()
                                        + " registrado exitosamente");

                } else if (signUpRequest.getRole().equals(RoleName.ROLE_COMPANY.getId())) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_COMPANY)
                                        .orElseThrow(() -> new AppException("Rol de empresa no establecido"));
                        user.setRoles(Collections.singleton(userRole));
                        log.info("Usuario " + signUpRequest.getUsername() + " " + RoleName.ROLE_COMPANY.getName()
                                        + " registrado exitosamente");
                } else {
                        return new ResponseEntity<>("Id role no establecido", HttpStatus.CONFLICT);
                }

                user.setActive(false);

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();

                emailService.emailSend(signUpRequest.getEmail(), signUpRequest.getName(), signUpRequest.getUsername(),
                                signUpRequest.getPassword());

                return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado exitosamente"));
        }

        @PutMapping("/signupwork")
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

                if (signUpRequest.getRole().equals(RoleName.ROLE_ADMIN.getId())) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                        .orElseThrow(() -> new AppException("Rol de admin no establecido"));
                        user.setRoles(Collections.singleton(userRole));
                        log.info("Usuario " + signUpRequest.getUsername() + " " + RoleName.ROLE_ADMIN.getName()
                                        + " registrado exitosamente");

                } else if (signUpRequest.getRole().equals(RoleName.ROLE_EMPLOYE.getId())) {
                        Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYE)
                                        .orElseThrow(() -> new AppException("Rol de empleado no establecido"));
                        user.setRoles(Collections.singleton(userRole));
                        log.info("Usuario " + signUpRequest.getUsername() + " " + RoleName.ROLE_EMPLOYE.getName()
                                        + " registrado exitosamente");

                } else {
                        return new ResponseEntity<>("Id role no establecido", HttpStatus.CONFLICT);
                }

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                                .buildAndExpand(result.getUsername()).toUri();
                emailService.emailSend(signUpRequest.getEmail(), signUpRequest.getName(), signUpRequest.getUsername(),
                                signUpRequest.getPassword());

                return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado exitosamente"));
        }

        @PutMapping("/requestid/{username}")
        public ResponseEntity<?> obtenerID(@PathVariable(value = "username") String username) throws JoseException {

                try {
                        User user = userRepository.findByUsername(username)
                                        .orElseThrow(() -> new IllegalStateException("Usuario no existe."));

                        user.getId();

                        return ResponseEntity.ok(user.getId());
                } catch (Exception e) {
                        log.error("HA OCURRIDO UN ERROR " + e.getMessage());
                        String[] timestampError = Util.getCurrentTimeStamp().split(";");
                        mensajes = Util.Codigos.MALPARAMETROS.split(";");
                        mensajeError = new MensajeVO(timestampError[0], timestampError[1], e.getMessage(), mensajes[0]);
                        salida.setPeticion(mensajeError);
                        return new ResponseEntity<ResultadoVO>(salida, HttpStatus.CONFLICT);
                }

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