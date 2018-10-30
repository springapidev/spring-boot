package com.coderbd.springoauthrestengine.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "privilize")
@EqualsAndHashCode
@ToString
public class Privilize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Please Enter Privilize Name")
    @Column(name = "privilize_name", unique = true)
    String privilizeName;

    public Privilize(String privilizeName) {
        this.privilizeName = privilizeName;
    }

    public Long getId() {
        return id;
    }

    public Privilize() {
    }

    public String getPrivilizeName() {
        return privilizeName;
    }
}
