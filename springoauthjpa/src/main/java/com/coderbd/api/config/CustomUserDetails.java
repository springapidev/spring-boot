package com.coderbd.api.config;

import com.coderbd.api.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provides a basic implementation of the UserDetails interface
 */
public class CustomUserDetails implements  UserDetails {
    private Collection<? extends GrantedAuthority> authorities;

    private String password;
    private String username;

    public CustomUserDetails(Collection<? extends GrantedAuthority> authorities, String password, String username) {
        this.authorities = authorities;
        this.password = password;
        this.username = username;
    }

    private Collection<? extends GrantedAuthority> translate(List<Role> roles){
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for(Role role : roles){
            String roleName=role.getRoleName().toUpperCase();
            if(!roleName.startsWith("ROLE_")){
            //Make sure that all roles start with "ROLE_"
           roleName="ROLE_"+roleName;
           grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
           }

            }

        return authorities;
         }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
