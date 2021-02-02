package com.example.demo.Models;

import com.example.demo.Models.Caroperation;
import com.example.demo.Models.Log;
import com.example.demo.Models.Purchase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public @Entity class Appuser implements UserDetails
{

    private @Id @GeneratedValue int id;

    @NotNull
    private String name;

    @NotNull
    private String username;

    private String token;

    @NotNull
    //@JsonIgnore // not return in response
    private String password;


    private Date created_at;
    private Date updated_at;


    @OneToMany(mappedBy = "appuser")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "appuser")
    private List<Caroperation> caroperations;

    @OneToMany(mappedBy = "appuser")
    private List<Log> logs;


    public Appuser()
    {
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }

    public Appuser(int id , String name ,String username ,String password )
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }

    public void update(String name ,String username ,String password )
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.updated_at = new Date();
        this.updated_at.setTime(System.currentTimeMillis());
    }

    public static String getAuthUsername()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticationName = authentication.getName();
        return authenticationName;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public String toString() {
        return String.format("Name : %s - Username : %s - password : %s",this.name,this.username,this.password);
    }


}
