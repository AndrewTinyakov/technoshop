package spaceprog.technoshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import spaceprog.technoshop.payload.request.securityRequest.LoginRequest;
import spaceprog.technoshop.payload.request.securityRequest.SingUpRequest;

import javax.validation.Valid;

public interface AuthenticationController {
    ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest singupRequest);

    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

//    public boolean findUserToResetPassword(String login, String email);

}
