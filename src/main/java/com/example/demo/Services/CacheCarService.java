package com.example.demo.Services;

import com.example.demo.Models.Car;
import com.example.demo.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheCarService
{

    @Autowired
    private CarRepository carRepository;


    public Iterable<Car> getAllCars()
    {
        return this.carRepository.findAll();
    }


    @Cacheable(value = "CarCache" , key = "#id")
    public Car getCarById(int id) {

        return carRepository.findById(id)
                .orElse(null);
    }

    @Cacheable(value = "CarCache" , key = "#name")
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

    @CacheEvict(value = "CarCache" , key = "#id")
    public Car delete(int id)
    {
        Car car = this.carRepository.findById(id).orElse(null);
        if (car != null)
        {
            this.carRepository.deleteById(id);
        }


        return car;
    }

    @CachePut(value = "CarCache" , key = "#id")
    public Car update(Car c, int id)
    {
        Car oldc = this.carRepository.findById(id)
                .orElse(null);

        oldc.update(c.getName(),c.getPrice(),c.getNumber_of_seats(),c.getIspurchase());

        this.carRepository.save(oldc);

        return oldc;
    }
}
