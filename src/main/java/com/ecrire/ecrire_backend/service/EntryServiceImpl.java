package com.ecrire.ecrire_backend.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService{

    @Override
    public List<String> getEntries() {
        List<String> entriesList= new ArrayList<>();
        entriesList.add("entries");
        return entriesList;
    }
}
