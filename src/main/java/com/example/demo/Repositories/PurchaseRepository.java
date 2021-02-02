package com.example.demo.Repositories;

import com.example.demo.Models.Purchase;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;


@Repository
public interface PurchaseRepository extends CrudRepository<Purchase,Integer>
{
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query(value = "SELECT p FROM Purchase p where p.id = ?1")
    public Purchase findPurchaseWithPessimisticLock(int id);




    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query(value = "SELECT p FROM Purchase p where p.month = ?1")
    public List<Purchase> findPurchaseInMonthWithPessimisticLock(int month);


    public List<Purchase> findPurchaseByMonth(int month);




}
