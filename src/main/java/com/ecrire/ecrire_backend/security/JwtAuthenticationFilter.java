package com.ecrire.ecrire_backend.security;

import com.ecrire.ecrire_backend.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629"; // Replace with a secure key
    private static final String HEADER = "Authorization";

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

//        System.out.println(" inside JwtAuthenticationFileter do Filter");
//        System.out.println(request.toString());
        String token = request.getHeader(HEADER);
        System.out.println(token);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Claims claims = jwtService.extractAllClaims(token);
            String username = claims.getSubject();
            System.out.println("Authenticated user: " + username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, userService.getRoles(username)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("SecurityContext set with authentication: " + authentication); // Debugging
        }

        filterChain.doFilter(request, response);
    }


}