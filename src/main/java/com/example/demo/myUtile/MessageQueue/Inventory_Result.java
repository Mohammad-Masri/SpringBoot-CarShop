package com.example.demo.myUtile.MessageQueue;

import com.example.demo.Models.Car;

import java.io.Serializable;
import java.util.List;

public class Inventory_Result implements Serializable
{
    private List<Car> cars;
    private Double price;

    public Inventory_Result(List<Car> cars , Double price)
    {
        this.cars = cars;
        this.price = price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
