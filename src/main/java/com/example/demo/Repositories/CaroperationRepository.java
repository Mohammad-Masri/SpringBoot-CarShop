package com.example.demo.Repositories;


import com.example.demo.Models.Caroperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaroperationRepository extends CrudRepository<Caroperation,Integer>
{

}
