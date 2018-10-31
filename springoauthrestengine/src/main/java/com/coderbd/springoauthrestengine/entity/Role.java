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
    private final String rolename;

    @ManyToMany
    @JoinTable(
            name = "role_privilize", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilize_id")
    )
    private final Set<Privilize> privilizes;

    public Role(String rolename, Set<Privilize>privilizes) {
        this.rolename = rolename;
        this.privilizes = privilizes;
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
