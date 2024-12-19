package com.ecrire.ecrire_backend.binding;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table
public class Role {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roles;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roles='" + roles + '\'' +
                ", user=" + user +
                '}';
    }
}
