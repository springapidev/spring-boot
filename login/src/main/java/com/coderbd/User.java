package com.coderbd;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 86)
    private String firstName;
    @Column(length = 86,name = "lastname")
    private String lastName;

    @Column(length = 15)
    private String mobile;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(name = "member_id")
    private String memberId;
    private boolean status;


    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

    public User(User user){
        this.roles=user.roles;
        this.password = user.password;
        this.memberId = user.memberId;
        this.status = user.status;
    }

    public User() {
    }
}
