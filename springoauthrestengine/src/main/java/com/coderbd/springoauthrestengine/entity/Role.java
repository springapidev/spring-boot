package com.coderbd.springoauthrestengine.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@EqualsAndHashCode
@ToString
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
}
