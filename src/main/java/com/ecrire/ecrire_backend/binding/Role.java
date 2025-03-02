package com.ecrire.ecrire_backend.binding;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table
public class Role implements GrantedAuthority {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    //just remember to save roles in ROLE_USER, ROLE_ADMIN format
    private String roles;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Nonnull
    private User user;

    public Role(){}
    public Role(String roles, User user){
        this.roles=roles;
        this.user=user;
    }


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

    @Override
    public String getAuthority() {
        return roles;
    }
}
