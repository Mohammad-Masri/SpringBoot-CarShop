package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public @Entity  class Purchase
{

    private @Id @GeneratedValue int id;

    @NotNull
    private double purchase_price;

    private int month;

//    @OneToOne(mappedBy = "purchase")
//    private Car car;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;


    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name="appuser_id")
    private Appuser appuser;

    private Date created_at;
    private Date updated_at;

    public Purchase()
    {
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());


        LocalDate currDate = created_at.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.month = currDate.getMonthValue();
    }

    public Purchase( double purchase_price ,Car car , Customer customer , Appuser user )
    {
        this.purchase_price = purchase_price;
        this.car = car;
        this.appuser = user;
        this.customer = customer;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());

        LocalDate currDate = created_at.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.month = currDate.getMonthValue();
    }


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }
    public double getPurchase_price() {
        return purchase_price;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @JsonIgnore
    public Car getCar() {
        return car;
    }

    public void setAppuser(Appuser appuser) {
        this.appuser = appuser;
    }

    public Appuser getAppuser() {
        return appuser;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    public void update(double purchase_price  )
    {
        this.purchase_price = purchase_price;
        this.updated_at = new Date();
        this.updated_at.setTime(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "id : "+this.id+" - purchase price : "+this.purchase_price+" - date : "+this.created_at +" - car id : "+this.car.getId()
                +" - user id : "+this.appuser;
    }
}
