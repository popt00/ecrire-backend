package com.ecrire.ecrire_backend.service;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.binding.User;
import com.ecrire.ecrire_backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public String upsert(User user) {
        userRepository.save(user);
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
}
