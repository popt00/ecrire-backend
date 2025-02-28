package com.ecrire.ecrire_backend.rest;

import com.ecrire.ecrire_backend.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test", method= RequestMethod.GET)
public class TestingController {
    @Autowired
    private JwtService jwtService;

    @GetMapping("/jwt/check/{token}")
    public ResponseEntity<String> validateToken(@PathVariable String token){
        return new ResponseEntity<>(jwtService.extractUsername(token), HttpStatus.OK);
    }
    @GetMapping("/jwt/generate/{username}")
    public ResponseEntity<String>  generateToken(@PathVariable String username){
        return new ResponseEntity<>(jwtService.GenerateToken(username), HttpStatus.OK);
    }
}
