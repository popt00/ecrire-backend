package com.ecrire.ecrire_backend.service;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.binding.User;

import java.util.List;

public interface EntryService {
    public List<Entry> findByKeyword(User user, String str);
    public List<Entry> findByUserByUserId(Integer userId);
    public String upsert(Entry entry);
    public List<Entry> getEntries();
    public Entry getEntryById(Integer id);
    public String deleteById(Integer id);
}
