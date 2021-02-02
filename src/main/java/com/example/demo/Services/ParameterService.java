package com.example.demo.Services;

import com.example.demo.Models.Parameter;
import com.example.demo.Repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ParameterService
{

    @Autowired
    private ParameterRepository parameterRepository;


    public Iterable<Parameter> getAllParameters()
    {
        return this.parameterRepository.findAll();
    }


    public Parameter getParameterById(String key) {

        return parameterRepository.findById(key)
                .orElse(null);
    }



    public int getNumberOfSeats() {

        String key = "number_of_seats";
        Parameter parameter = parameterRepository.findById(key).orElse(null);


        int numberOfSeats = Integer.parseInt(parameter.getValue()) ;

        return numberOfSeats;

    }

    public double getProfitRatio() {
        String key = "profit_ratio";

        Parameter parameter = parameterRepository.findById(key).orElse(null);


        double profit_ratio = Double.parseDouble(parameter.getValue()) ;

        return profit_ratio;
    }

    public int getMinPrice() {
        String key = "min_price";

        Parameter parameter = parameterRepository.findById(key).orElse(null);


        int min_price = Integer.parseInt(parameter.getValue()) ;

        return min_price;
    }




    public Parameter save(Parameter c)
    {
        return this.parameterRepository.save(c);
    }

    public void delete(String key)
    {
        this.parameterRepository.deleteById(key);
    }

    public Parameter update(Parameter p, String key)
    {
        Parameter oldp = this.parameterRepository.findById(key)
                .orElse(null);

        oldp.update(p.getMykey(),p.getValue());

        this.parameterRepository.save(oldp);

        return oldp;
    }
}
