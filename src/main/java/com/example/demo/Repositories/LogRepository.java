package com.example.demo.Repositories;


import com.example.demo.Models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends CrudRepository<Log,Integer>
{

}
