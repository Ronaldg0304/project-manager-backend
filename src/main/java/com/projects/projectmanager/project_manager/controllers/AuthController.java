package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.entities.AuthRequest;
import com.projects.projectmanager.project_manager.security.JwtService;
import com.projects.projectmanager.project_manager.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        System.out.println("Estamos en el endPoint: /login");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails user = userDetailsServiceImpl.loadUserByUsername(request.getUsername());
        return jwtService.getToken(user);
    }

    @GetMapping("/role")
    public ResponseEntity<?> getUserDetails(Authentication authentication) {
        // El Authentication ya contiene los datos del usuario autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("username", userDetails.getUsername());
        response.put("roles", userDetails.getAuthorities());

        return ResponseEntity.ok(response);
    }
}
