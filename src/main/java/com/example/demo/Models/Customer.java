package com.example.demo.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public @Entity class Customer
{

    private @Id @GeneratedValue  int id;

    @NotNull
    private String full_name;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    private Date created_at;
    private Date updated_at;

    public Customer()
    {
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }

    public Customer( String full_name )
    {
        this.full_name = full_name;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getFull_name() {
        return full_name;
    }

    public void update(String full_name )
    {
        this.full_name = full_name;
        this.updated_at = new Date();
        this.updated_at.setTime(System.currentTimeMillis());
    }

    @Override
    public String toString()
    {
        return String.format("id : %d - name : %s ",this.id ,this.full_name);
    }
}
