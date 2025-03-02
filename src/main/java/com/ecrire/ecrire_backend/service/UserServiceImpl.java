package com.ecrire.ecrire_backend.service;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.binding.Role;
import com.ecrire.ecrire_backend.binding.User;
import com.ecrire.ecrire_backend.repo.RoleRepository;
import com.ecrire.ecrire_backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public String upsert(User user, List<Role> roles) {
        userRepository.save(user);
        roleRepository.saveAll(roles);
        return "Success";
    }

    @Override
    public List<User> getEntries() {
        return userRepository.findAll();
    }

    @Override
    public User getEntryById(Integer id) {
        Optional<User> userOptional= userRepository.findById(id);
        if(userOptional.isPresent())return userOptional.get();
        return null;
    }

    @Override
    public String delete(Integer id) {
        Optional<User> userOptional= userRepository.findById(id);
        if(userOptional.isEmpty())return "User Does not Exist";
        userRepository.delete(userOptional.get());
        return "Success";
    }

    @Override
    public List<Role> getRoles(String username) {
        List<Role> roles = roleRepository.findByUser(userRepository.findByUsername(username));
        return roles;
    }

}
