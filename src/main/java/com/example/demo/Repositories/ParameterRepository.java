package com.example.demo.Repositories;

import com.example.demo.Models.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParameterRepository extends CrudRepository<Parameter,String>
{

}
