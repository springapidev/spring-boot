package com.coderbd.springoauthrestengine.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotEmpty(message = "*Please enter username")
    @Column(name = "username", unique = true)
    private final String userName;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Email
    @NotEmpty(message = "*Please enter Email Address")
    @Column(name = "email", unique = true)
    private final String email;

    @Length(min = 11, message = "*Your mobile must have at least 11 characters")
    @NotEmpty(message = "*Please enter your mobile")
    @Column(name = "mobile", unique = true)
    private final String mobile;

    @Column(name = "joining_date")
    private final Date joiningDate;

    @Column(name = "is_ctivated")
    private final boolean isActivated;

    @Size(min = 0, max = 100)
    @Column(name = "activation_key")
    private final String activationKey;

    @Size(min = 0, max = 100)
    @Column(name = "reset_pass_key")
    private final String resetPasswordKey;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String userName, String firstName, String lastName, String email,String mobile, Date joiningDate,boolean isActivated,String activationKey,String resetPasswordKey) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.joiningDate = joiningDate;
        this.isActivated=isActivated;
        this.activationKey=activationKey;
        this.resetPasswordKey=resetPasswordKey;
    }
}
