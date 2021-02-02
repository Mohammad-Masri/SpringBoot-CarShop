package com.example.demo.Services;

import com.example.demo.Models.Customer;
import com.example.demo.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{

    @Autowired
    private CustomerRepository customerRepository;


    public Iterable<Customer> getAllCustomers()
    {
        return this.customerRepository.findAll();
    }


    public Customer getCustomerById(int id) {

        return customerRepository.findById(id)
                .orElse(null);
    }

    public Customer save(Customer c)
    {
        return this.customerRepository.save(c);
    }

    public void delete(int id)
    {
        this.customerRepository.deleteById(id);
    }

    public Customer update(Customer c, int id)
    {
        Customer oldc = this.customerRepository.findById(id)
                .orElse(null);

        oldc.update(c.getFull_name());

        this.customerRepository.save(oldc);

        return oldc;
    }
}
