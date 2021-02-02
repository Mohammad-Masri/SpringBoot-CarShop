package com.example.demo.Services;

import com.example.demo.Models.Purchase;
import com.example.demo.Repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseService
{

    @Autowired
    private PurchaseRepository purchaseRepository;


    public Iterable<Purchase> getAllPurchases()
    {
        return this.purchaseRepository.findAll();
    }

    @Transactional
    public Purchase getAllPurchasesWithLock(int id)
    {
        return this.purchaseRepository.findPurchaseWithPessimisticLock(id);
    }

    @Transactional
    public List<Purchase> getAllPurchasesInMonthWithLock(int month)
    {
        return this.purchaseRepository.findPurchaseInMonthWithPessimisticLock(month);
    }




    public Purchase getPurchaseById(int id) {

        return purchaseRepository.findById(id)
                .orElse(null);
    }

    public Purchase save(Purchase c)
    {
        return this.purchaseRepository.save(c);
    }

    public void delete(int id)
    {
        this.purchaseRepository.deleteById(id);
    }

    public Purchase update(Purchase p, int id)
    {
        Purchase oldp = this.purchaseRepository.findById(id)
                .orElse(null);

        oldp.update(p.getPurchase_price());

        this.purchaseRepository.save(oldp);

        return oldp;
    }
}
