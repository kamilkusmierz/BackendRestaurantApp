package com.RestServer.jwtauthentication.auth;

import javax.validation.Valid;
import com.RestServer.jwtauthentication.message.request.LoginForm;
import com.RestServer.jwtauthentication.message.request.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthControlerRest {
    @Autowired
    AuthControler AuthControler;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        try {
            return ResponseEntity.ok(AuthControler.authenticateUser(loginRequest));
        } catch (Exception ex) {
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

        try {
            return new ResponseEntity<>(AuthControler.registerUser(signUpRequest), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}