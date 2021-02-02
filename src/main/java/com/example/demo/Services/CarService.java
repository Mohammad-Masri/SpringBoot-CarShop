package com.example.demo.Services;

import com.example.demo.Models.Car;
import com.example.demo.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService
{

    @Autowired
    private CarRepository carRepository ;


    public Iterable<Car> getAllCars()
    {
        return this.carRepository.findAll();
    }


    public Car getCarById(int id) {

        return carRepository.findById(id)
                .orElse(null);
    }

    public List<Car> getCarByName(String name) {

        return carRepository.findByNameContains(name);
    }

    public List<Car> getCarIsNotPurchase() {

        return carRepository.findByIspurchase(false);
    }

    public Car save(Car c)

    {
        return this.carRepository.save(c);
    }

    public Car delete(int id)
    {
        Car car = this.carRepository.findById(id).orElse(null);
        if (car != null)
        {
            this.carRepository.deleteById(id);
        }


        return car;
    }

    public Car update(Car c, int id)
    {
        Car oldc = this.carRepository.findById(id)
                .orElse(null);

        oldc.update(c.getName(),c.getPrice(),c.getNumber_of_seats(),c.getIspurchase());

        this.carRepository.save(oldc);

        return oldc;
    }
}
