package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final Users user;

    @Getter
    private final Student student;
    private final List<GrantedAuthority> authorities;
    public MyUserDetails(Users user, Student student, List<GrantedAuthority> authorities) {
        this.user = user;
        this.student = student;
        this.authorities = authorities;
    }

    public MyUserDetails(Users user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.student = null;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {return user.getUsername();
    }


    public Users getUser() {
        return user;
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