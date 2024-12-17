package com.ecrire.ecrire_backend.repo;

import com.ecrire.ecrire_backend.binding.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EntryRepository extends JpaRepository<Entry, Serializable> {
}
