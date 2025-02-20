package com.ecrire.ecrire_backend.rest;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.binding.User;
import com.ecrire.ecrire_backend.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class DashBoardRestController {
    @Autowired
    EntryService entryService;

    @GetMapping("/entry/{id}")
    public ResponseEntity<Entry> getEntry(@PathVariable Integer id){
        Entry entryById = entryService.getEntryById(id);
        return new ResponseEntity<>(entryById, HttpStatus.OK);
    }
    @GetMapping("/entry")
    public ResponseEntity<List<Entry>> getAllentries(){
//        System.out.println("in this get");
        List<Entry> listEntries = entryService.getEntries();
        return new ResponseEntity<>(listEntries, HttpStatus.OK);
    }

//    @GetMapping("/entry/search/{str}")
//    public ResponseEntity<List<Entry>> getEntriesSearch(@PathVariable String str){
//        List<Entry> listEntries = entryService.findByKeyword(str);
//        return new ResponseEntity<>(listEntries, HttpStatus.OK);
//    }

    @GetMapping("/entry/user/{id}")
    public ResponseEntity<List<Entry>> getEntriesSearch(@PathVariable Integer id){
        List<Entry> listEntries = entryService.findByUserByUserId(id);
        return new ResponseEntity<>(listEntries, HttpStatus.OK);
    }

    @PostMapping("/entry/")
    public ResponseEntity<String> setEntries(@RequestBody Entry entry){
//        System.out.println("in this post");
        String message= entryService.upsert(entry);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PutMapping("/entry")
    public ResponseEntity<String> putEntries(@RequestBody Entry entry){
        String message= entryService.upsert(entry);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @DeleteMapping("/entry/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Integer id){
        return new ResponseEntity<>(entryService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/user")
    public Principal getUser(Principal user){
        return  user;
    }
}
