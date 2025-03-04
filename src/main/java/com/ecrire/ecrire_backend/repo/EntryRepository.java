package com.ecrire.ecrire_backend.repo;

import com.ecrire.ecrire_backend.binding.Entry;
import com.ecrire.ecrire_backend.binding.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Serializable> {
    List<Entry> findByUser_Userid(Integer userId);
    List<Entry> findByUserUsername(String username);
}
