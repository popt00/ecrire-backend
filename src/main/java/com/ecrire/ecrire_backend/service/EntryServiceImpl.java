package com.ecrire.ecrire_backend.service;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.binding.User;
import com.ecrire.ecrire_backend.repo.EntryRepository;
import com.ecrire.ecrire_backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService{

    @Autowired
    EntryRepository entryRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Entry> findByKeyword(User user, String str) {
        return List.of();
    }

    @Override
    public List<Entry> findByUserByUserId(Integer userId) {
        return entryRepository.findByUser_Userid(userId);
    }

    @Override
    public String upsert(Entry entry) {
//        System.out.println(entry.toString());
//        if(entry.getUser()==null )throw new IllegalArgumentException("User information is missing or incomplete.");
        Optional<User> userOptional= userRepository.findById(entry.getUser().getUserid());
        if(userOptional.isEmpty()){
            throw  new RuntimeException("User not found");
        }
        User user= userOptional.get();
        entry.setUser(user);
        entryRepository.save(entry);
        return "Success";
    }

    @Override
    public List<Entry> getEntries(String username) {
        List<Entry> entriesList= entryRepository.findByUserUsername(username);
        return entriesList;
    }

    @Override
    public Entry getEntryById(Integer id) {
        Optional<Entry> entry=entryRepository.findById(id);
        if(entry.isPresent())return  entry.get();
        return null;
    }

    @Override
    public String deleteById(Integer id) {
        Optional<Entry> entryOptional= entryRepository.findById(id);
        if(entryOptional.isEmpty())return "User Does not Exist";
        entryRepository.delete(entryOptional.get());
        return "Success";
    }

}
