package com.ecrire.ecrire_backend.service;
import com.ecrire.ecrire_backend.binding.Role;
import com.ecrire.ecrire_backend.binding.User;

import java.util.List;

public interface UserService {
    public String upsert(User user, List<Role> roles);
    public List<User> getEntries();
    public User getEntryById(Integer id);
    public String delete(Integer id);
    public List<Role> getRoles(String username);

}
