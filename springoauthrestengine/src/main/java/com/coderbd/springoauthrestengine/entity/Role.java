package com.coderbd.springoauthrestengine.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "rolename", unique = true)
    @NotEmpty(message = "Please Enter Rolename")
    private String rolename;

    @ManyToMany
    @JoinTable(
            name = "role_privilize", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilize_id")
    )
    private Set<Privilize> privilizes;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public Set<Privilize> getPrivilizes() {
        return privilizes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setPrivilizes(Set<Privilize> privilizes) {
        this.privilizes = privilizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(rolename, role.rolename) &&
                Objects.equals(privilizes, role.privilizes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rolename, privilizes);
    }
}
