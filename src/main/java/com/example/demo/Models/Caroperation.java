package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public @Entity class Caroperation
{
    private @Id
    @GeneratedValue
    int id;

    @NotNull
    private String operation;

    private Date date;


    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;


    @ManyToOne
    @JoinColumn(name="appuser_id")
    private Appuser appuser;

    public Caroperation()
    {
        this.date = new Date();
        this.date.setTime(System.currentTimeMillis());
    }

    public Caroperation(int id , String operation )
    {
        this.id = id ;
        this. operation = operation;
        this.date = new Date();
        this.date.setTime(System.currentTimeMillis());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setAppuser(Appuser appuser) {
        this.appuser = appuser;
    }

    public Appuser getAppuser() {
        return appuser;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }


    @Override
    public String toString() {
        return String.format("id : %d - operation : %s - car_id : %d - user_id : %d"
                ,this.id,this.operation,this.car.getId(),this.appuser.getId());
    }
}
