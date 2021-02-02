package com.example.demo.Services;

import com.example.demo.Models.Parameter;
import com.example.demo.Repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheParameterService
{

    @Autowired
    private ParameterRepository parameterRepository;


    public Iterable<Parameter> getAllParameters()
    {
        return this.parameterRepository.findAll();
    }



    //    @Cacheable(value = "ParameterCache" , key = "#id")
    //@CachePut(cacheNames = "ParameterCache", key = "#result.id + #result.mykey")
//    @Cacheable(value = "ParameterCache" , unless = "#result != null", key="{#result.mykey, #result.id}")
//    @Caching(
//            cacheable = {
//                    @Cacheable(value = "ParameterCache" , key = "new org.springframework.cache.interceptor.SimpleKey(#result.id)"),
//                    @Cacheable(value = "ParameterCache" , unless = "#result != null" , key = "new org.springframework.cache.interceptor.SimpleKey(#result._key)")
//            }
//    )

    @Cacheable(value = "ParameterCache" , key = "#key")
    public Parameter getParameterById(String key)
    {

        return parameterRepository.findById(key).orElse(null);
    }


//    @Cacheable(value = "ParameterCache" , key = "#key")
//    public Parameter getParameterByKey(String key)
//    {
//        return parameterRepository.findByMykey(key);
//    }


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

    @CacheEvict(value = "ParameterCache" , key = "#key")
    public void deleteById(String key)
    {
        this.parameterRepository.deleteById(key);
    }

//    @CacheEvict(value = "ParameterCache" , key = "#key")
//    public void deleteByKey(String key)
//    {
//        Parameter p = this.parameterRepository.findByMykey(key);
//        this.parameterRepository.delete(p);
//    }




    //@CachePut(value = "ParameterCache" , key = "#id")





//    @Caching(
//            put = {
//                    @CachePut(value = "ParameterCache", key = "id"),
//                    @CachePut(value = "ParameterCache", key = "#result.mykey")
//            }
//    )

    @CachePut(cacheNames = "ParameterCache", key = "#key")
    public Parameter update(Parameter p, String key)
    {
        Parameter oldp = this.parameterRepository.findById(key)
                .orElse(null);

        oldp.update(p.getMykey(),p.getValue());

        this.parameterRepository.save(oldp);

        return oldp;
    }

//    @CachePut(value = "ParameterCache" , key = "#key")
//    public Parameter update(Parameter p, String key)
//    {
//        Parameter oldp = this.parameterRepository.findByMykey(key);
//
//        oldp.update(p.getMykey(),p.getValue());
//
//        this.parameterRepository.save(oldp);
//
//        return oldp;
//    }

}
