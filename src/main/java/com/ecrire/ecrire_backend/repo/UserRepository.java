package com.ecrire.ecrire_backend.repo;

import com.ecrire.ecrire_backend.binding.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<User, Serializable> {
}
