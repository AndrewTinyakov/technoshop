package spaceprog.technoshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spaceprog.technoshop.model.User;
import spaceprog.technoshop.payload.request.securityRequest.LoginRequest;
import spaceprog.technoshop.payload.request.securityRequest.SingUpRequest;
import spaceprog.technoshop.payload.response.securityResponse.JwtResponse;
import spaceprog.technoshop.repository.RoleRepository;
import spaceprog.technoshop.security.ERole;
import spaceprog.technoshop.security.Role;
import spaceprog.technoshop.security.jwt.JwtUtils;
import spaceprog.technoshop.security.service.UserDetailsImpl;
import spaceprog.technoshop.service.UserService;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static spaceprog.technoshop.security.ERole.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthenticationControllerImpl implements AuthenticationController {

    AuthenticationManager authenticationManager;
    UserService userService;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    public AuthenticationControllerImpl(AuthenticationManager authenticationManager, UserService userService, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest singupRequest) {
        if (userService.existsByUsername(singupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        // Create new user's account
        User user = new User(singupRequest);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        userService.register(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }



}
