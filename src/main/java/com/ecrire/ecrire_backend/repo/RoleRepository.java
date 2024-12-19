package com.ecrire.ecrire_backend.repo;

import com.ecrire.ecrire_backend.binding.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RoleRepository extends JpaRepository<Role, Serializable> {
}
