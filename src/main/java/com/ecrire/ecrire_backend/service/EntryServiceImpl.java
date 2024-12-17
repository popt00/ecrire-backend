package com.ecrire.ecrire_backend.service;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.repo.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService{

    @Autowired
    EntryRepository entryRepository;

    @Override
    public String upsert(Entry entry) {
//        System.out.println(entry.toString());
        entryRepository.save(entry);
        return "Success";
    }

    @Override
    public List<Entry> getEntries() {
        List<Entry> entriesList= entryRepository.findAll();
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
