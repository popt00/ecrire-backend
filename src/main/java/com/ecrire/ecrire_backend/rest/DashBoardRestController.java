package com.ecrire.ecrire_backend.rest;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.binding.User;
import com.ecrire.ecrire_backend.service.EntryService;
import com.ecrire.ecrire_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class DashBoardRestController {
    @Autowired
    EntryService entryService;

    @Autowired
    UserService userService;

    @GetMapping("/entry")
    public ResponseEntity<List<Entry>> getAllentries(Authentication authentication){
        List<Entry> listEntries = entryService.getEntries(authentication.getName());
        return new ResponseEntity<>(listEntries, HttpStatus.OK);
    }

    @PostMapping("/entry")
    public ResponseEntity<String> setEntries(@RequestBody Entry entry, Authentication authentication){
        entry.setUser(userService.getUserByUsername(authentication.getName()));
        String message= entryService.upsert(entry);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/entry")
    public ResponseEntity<String> putEntries(@RequestBody Entry entry, Authentication authentication){
        entry.setUser(userService.getUserByUsername(authentication.getName()));
        String message= entryService.upsert(entry);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/entry/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Integer id, Authentication authentication){
        if(entryService.getEntryById(id).getUser().getUsername() != authentication.getName()){
            return new ResponseEntity<>("id error", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(entryService.deleteById(id), HttpStatus.OK);
    }

//    @GetMapping("/entry/search/{str}")
//    public ResponseEntity<List<Entry>> getEntriesSearch(@PathVariable String str){
//        List<Entry> listEntries = entryService.findByKeyword(str);
//        return new ResponseEntity<>(listEntries, HttpStatus.OK);
//    }





}
