package com.coderbd.thymeleaftemplate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }


}
