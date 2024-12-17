package com.ecrire.ecrire_backend.service;
import com.ecrire.ecrire_backend.binding.User;

import java.util.List;

public interface UserService {
    public String upsert(User user);
    public List<User> getEntries();
    public User getEntryById(Integer id);
    public String delete(Integer id);

}
