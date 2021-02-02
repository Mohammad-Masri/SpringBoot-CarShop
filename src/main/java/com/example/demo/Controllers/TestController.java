package com.example.demo.Controllers;


import com.example.demo.Models.Car;
import com.example.demo.Models.Parameter;
import com.example.demo.Services.CacheCarService;
import com.example.demo.Services.CacheParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/")
public class TestController
{


    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private CacheCarService cacheCarService;

    @Autowired
    private CacheParameterService cacheParameterService;

    @GetMapping("get/test")
    public String getTest(@RequestParam(value = "full_name", required = false) String full_name , Model model){
        return "test";
    }

    @PostMapping("test/store")
    public String postTest(@RequestParam("full_name") String full_name  )
    {

        System.out.println("safafae wfwfwafjfjw : "+full_name);

        return "done";
    }
//
//    @GetMapping("cache/test1")
//    public void getcacheTest1()
//    {
//        logger.info(".... Fetching books");
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("number_of_seats"));
//        logger.info("isbn-4567 -->" + paraRepo.findByMykey("profit_ratio"));
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("min_price"));
//        logger.info("isbn-4567 -->" + paraRepo.findByMykey("number_of_seats"));
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("profit_ratio"));
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("min_price"));
//    }
//
//    @GetMapping("cache/test2")
//    public void getcacheTest2()
//    {
//        logger.info(".... Fetching books");
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("number_of_seats"));
//        logger.info("isbn-4567 -->" + paraRepo.findByMykey("profit_ratio"));
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("min_price"));
//    }
//
//    @GetMapping("cache/all")
//    public void getcacheTestall()
//    {
//
//        Iterable<Parameter> parameters = paraRepo.findAll();
//        List<Parameter> parameterList = new ArrayList<>();
//        parameters.forEach(parameterList::add);
//
//        logger.info(".... Fetching books");
//        for (Parameter p :parameterList) {
//            logger.info("isbn-1234 -->" + p);
//        }
//    }

    @GetMapping("get/car/from/cache/{id}")
    public Car getCar(@PathVariable int id)
    {
        return cacheCarService.getCarById(id);
    }

    @GetMapping("delete/car/from/cache/{id}")
    public Car deleteCar(@PathVariable int id)
    {
        return cacheCarService.delete(id);
    }

    @PutMapping("update/car/from/cache/{id}")
    public Car deleteCar(@RequestBody Car car ,@PathVariable int id)
    {
        return cacheCarService.update(car ,id);
    }


    @GetMapping("get/parameter/from/cache/{key}")
    public Parameter getParameter(@PathVariable String key)
    {
        return cacheParameterService.getParameterById(key);
    }



}
