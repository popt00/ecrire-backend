package com.ecrire.ecrire_backend.rest;

import com.ecrire.ecrire_backend.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashBoardRestController {
    @Autowired
    EntryService entryService;

    @GetMapping("/entry")
    public ResponseEntity<List<String>> getAllentries(){
        return new ResponseEntity<>(entryService.getEntries(), HttpStatus.OK);
    }
}
