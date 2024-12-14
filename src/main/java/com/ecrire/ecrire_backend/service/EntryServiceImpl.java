package com.ecrire.ecrire_backend.service;

import org.springframework.stereotype.Service;

@Service
public class EntryServiceImpl implements EntryService{

    @Override
    public String getEntries() {
        return "Entry 1";
    }
}
