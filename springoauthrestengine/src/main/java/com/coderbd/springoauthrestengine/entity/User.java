package com.coderbd.springoauthrestengine.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActivated == user.isActivated &&
                Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(mobile, user.mobile) &&
                Objects.equals(joiningDate, user.joiningDate) &&
                Objects.equals(activationKey, user.activationKey) &&
                Objects.equals(resetPasswordKey, user.resetPasswordKey) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, firstName, lastName, email, mobile, joiningDate, isActivated, activationKey, resetPasswordKey, roles);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
