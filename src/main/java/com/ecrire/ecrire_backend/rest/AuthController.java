package com.ecrire.ecrire_backend.rest;

import com.ecrire.ecrire_backend.binding.classes.LoginRequest;
import com.ecrire.ecrire_backend.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


//    @CrossOrigin(origins = {"http://localhost:5173","http://localhost:3000"})
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        System.out.println("heyy : "+loginRequest.toString());
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),loginRequest.getPassword())
        );
        System.out.println("authentication done");
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok(jwtService.GenerateToken(loginRequest.getUsername()));
        }
        return ResponseEntity.ok("nottoken");
    }




}
