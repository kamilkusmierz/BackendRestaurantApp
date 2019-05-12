package com.RestServer.jwtauthentication.auth;

import java.util.HashSet;
import java.util.Set;

import com.RestServer.jwtauthentication.message.request.LoginForm;
import com.RestServer.jwtauthentication.message.request.SignUpForm;
import com.RestServer.jwtauthentication.message.response.JwtResponse;
import com.RestServer.jwtauthentication.message.response.ResponseMessage;
import com.RestServer.jwtauthentication.model.Role;
import com.RestServer.jwtauthentication.model.RoleName;
import com.RestServer.jwtauthentication.model.User;
import com.RestServer.jwtauthentication.repository.RoleRepository;
import com.RestServer.jwtauthentication.repository.UserRepository;
import com.RestServer.jwtauthentication.security.jwt.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthControlerImpl implements AuthControler {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public JwtResponse authenticateUser(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());

    }

    @Override
    public ResponseMessage registerUser(SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseMessage("Fail -> Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseMessage("Fail -> Email is already in use!");
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
            case "admin":
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(adminRole);

                break;
            default:
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseMessage("User registered successfully!");
    }

}