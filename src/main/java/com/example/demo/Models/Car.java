package com.example.demo.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public @Entity class Car
{

    private @Id @GeneratedValue  int id;

    @NotNull
    private String name;

    @NotNull
    private double price;

    @NotNull
    private int number_of_seats;

    @NotNull
    private boolean ispurchase;

    private int version;


    @OneToOne(mappedBy = "car")
    private Purchase purchase;

    @OneToMany(mappedBy = "car")
    private List<Caroperation> car_operations;

    private Date created_at;
    private Date updated_at;

    public Car()
    {
        this.version = 0;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());
    }

    public Car(int id , String name ,  double price , int number_of_seats , Date created_at)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.number_of_seats = number_of_seats;
        this.ispurchase = false;
        this.created_at = created_at;

        this.version = 0;

    }

    public Car(int id , String name ,  double price , int number_of_seats )
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.number_of_seats = number_of_seats;
        this.ispurchase = false;
        this.created_at = new Date();
        this.created_at.setTime(System.currentTimeMillis());

        this.version = 0;

    }

    public static Car make_packup(Car c)
    {
        Car car = new Car();
        car.setIspurchase(c.getIspurchase());
        car.setNumber_of_seats(c.getNumber_of_seats());
        car.setId(c.getId());
        car.setPrice(c.getPrice());
        car.setName(c.getName());
        car.setVersion(c.getVersion());
        car.setCreated_at(c.getUpdated_at());
        car.setUpdated_at(c.getUpdated_at());
        return car;
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

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }
    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setIspurchase(boolean ispurchase) {
        this.ispurchase = ispurchase;
    }
    public Boolean getIspurchase()
    {
        return ispurchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    @JsonIgnore
    public Purchase getPurchase() {
        return purchase;
    }

    public void setCar_operations(List<Caroperation> car_operations) {
        this.car_operations = car_operations;
    }

    @JsonIgnore
    public List<Caroperation> getCar_operations() {
        return car_operations;
    }

    public void update(String name , double price , int number_of_seats , boolean ispurchase )
    {
        this.name = name;
        this.price = price;
        this.number_of_seats = number_of_seats;
        this.ispurchase = ispurchase;
        this.version += 1;
        this.updated_at = new Date();
        this.updated_at.setTime(System.currentTimeMillis());
    }

    public void setAsPurchase()
    {
        this.ispurchase = true;
		this.version += 1;
    }

//    @Override
//    public String toString()
//    {
//        return
//                String.format("id : %d - name : %s - price : %d - number of seats : %d - version : %d"
//                ,this.id ,this.name ,this.price ,this.number_of_seats ,this.version )+" - date : "+this.created_at.toString();
//    }

    @Override
    public String toString()
    {
        return "id : "+this.id+" - name : "+this.name+" - price : "+this.price+" - number of seats : "+this.number_of_seats+" - version : "+this.version +" - date : "+this.created_at;
    }

}
