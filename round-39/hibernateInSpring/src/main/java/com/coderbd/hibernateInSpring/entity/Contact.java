package com.coderbd.hibernateInSpring.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    @Column(nullable = false)
    @Size(min = 3, max = 15)
    private String firstName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Column(unique = true, nullable = false)
    private String mobileNo;

    @OneToMany(mappedBy = "contact")
    private Set<ContactDetail> contactDetails;

    @OneToMany(mappedBy = "contacts")
    private Set<Hobby> hobbies;
}
