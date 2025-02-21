package com.ecrire.ecrire_backend.binding.classes;

import com.ecrire.ecrire_backend.binding.Role;
import com.ecrire.ecrire_backend.binding.User;
import com.ecrire.ecrire_backend.repo.RoleRepository;
import com.ecrire.ecrire_backend.repo.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

//@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        logger.debug("Entering in loadUserByUsername Method...");
        User user = userRepository.findByUsername(username);
        if(user == null){
//            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
//        logger.info("User Authenticated Successfully..!!!");
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> auths= new ArrayList<>();
                List<Role> listRoles= roleRepository.findByUser(user);
                for(Role role: listRoles){
                    auths.add(new SimpleGrantedAuthority(role.getRoles().toUpperCase()));

                }
                return auths;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }
        };
    }
}