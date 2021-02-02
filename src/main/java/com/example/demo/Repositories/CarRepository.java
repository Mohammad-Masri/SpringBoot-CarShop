package com.example.demo.Repositories;


import com.example.demo.Models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends CrudRepository<Car,Integer>
{



    public List<Car> findByNameContains(String name);
    public List<Car> findByIspurchase(Boolean ispurchase);


//    @Lock(LockModeType.PESSIMISTIC_READ)
//    public List<Car> findAllById(Iterable<Integer> ids);




}
