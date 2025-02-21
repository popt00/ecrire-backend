package com.ecrire.ecrire_backend.repo;

import com.ecrire.ecrire_backend.binding.Role;
import com.ecrire.ecrire_backend.binding.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Serializable> {
    List<Role> findByUser(User user
    );
}
