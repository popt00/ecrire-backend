package com.ecrire.ecrire_backend.rest;

import com.ecrire.ecrire_backend.binding.Role;
import com.ecrire.ecrire_backend.binding.User;
import com.ecrire.ecrire_backend.binding.classes.LoginRequest;
import com.ecrire.ecrire_backend.security.JwtService;
import com.ecrire.ecrire_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;


//    @CrossOrigin(origins = {"http://localhost:5173","http://localhost:3000"})
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
//        System.out.println("inside here");
        if(loginRequest.getUsername()==null && loginRequest.getEmail()==null)
            return  new ResponseEntity<>("username or email not found", HttpStatus.NOT_FOUND);
        if(loginRequest.getUsername()==null){
            String username = userService.getUserByEmail(loginRequest.getEmail()).getUsername();
            if(username==null)  new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
            loginRequest.setUsername(username);
        }
//        System.out.println("heyy : "+loginRequest.toString());
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),loginRequest.getPassword())
        );
//        System.out.println("authentication done");
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok(jwtService.GenerateToken(loginRequest.getUsername()));
        }
        return ResponseEntity.ok("failed");
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<String> signup(@RequestBody LoginRequest loginRequest) {
        User user= new User();
        user.setEmail(loginRequest.getEmail());
        user.setUsername(loginRequest.getUsername());
        user.setPassword("{noop}"+loginRequest.getPassword());
        user.setActive(1);
        Date date = new Date();
        user.setCreatedAt(date);
        Role role = new Role("ROLE_USER",user);
        List<Role> listRoles= new ArrayList<>();
        listRoles.add(role);

        userService.upsert(user,listRoles);
        return ResponseEntity.ok(user.getUsername()+" User Created");
    }




}
