package com.RestServer.jwtauthentication.auth;

import com.RestServer.jwtauthentication.message.request.LoginForm;
import com.RestServer.jwtauthentication.message.request.SignUpForm;
import com.RestServer.jwtauthentication.message.response.JwtResponse;
import com.RestServer.jwtauthentication.message.response.ResponseMessage;

public interface AuthControler {

    public JwtResponse authenticateUser(LoginForm loginRequest);

    public ResponseMessage registerUser(SignUpForm signUpRequest);
}
