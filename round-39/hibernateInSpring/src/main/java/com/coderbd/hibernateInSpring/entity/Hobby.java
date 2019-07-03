package com.coderbd.hibernateInSpring.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "hobby_contact",
            joinColumns = @JoinColumn(name="hobby_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<Contact> contacts;

}
