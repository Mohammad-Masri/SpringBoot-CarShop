package com.example.demo.Repositories;

import com.example.demo.Models.Appuser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<Appuser,Integer>
{
    public Appuser getByUsername(String username);
}
