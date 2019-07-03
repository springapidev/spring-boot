package com.coderbd.hibernateInSpring.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ContactDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    private String telType;
    private String telNumber;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

}
