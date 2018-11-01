package com.coderbd.springoauthrestengine.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "privilize")
public class Privilize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Please Enter Privilize Name")
    @Column(name = "privilize_name", unique = true)
    private String privilizeName;


    public Long getId() {
        return id;
    }



    public String getPrivilizeName() {
        return privilizeName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrivilizeName(String privilizeName) {
        this.privilizeName = privilizeName;
    }

    @Override
    public String toString() {
        return "Privilize{" +
                "id=" + id +
                ", privilizeName='" + privilizeName + '\'' +
                '}';
    }
}
