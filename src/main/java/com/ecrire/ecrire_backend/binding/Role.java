package com.ecrire.ecrire_backend.binding;

import jakarta.persistence.*;

@Entity
@Table
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn
}
