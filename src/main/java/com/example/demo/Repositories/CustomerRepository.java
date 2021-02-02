package com.example.demo.Repositories;

import com.example.demo.Models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer>
{

}
