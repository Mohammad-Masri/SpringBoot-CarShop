package com.example.demo.Controllers;


import com.example.demo.Models.*;
import com.example.demo.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@RequestMapping(value = "/")
public class PurchaseController
{
    private final String on_table = "purchase";

    @Autowired
    private CarService carService;

    @Autowired
    private CacheParameterService parameterService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LogService logService;


    @GetMapping("car/sale/{id}")
    public String purchaseCar(@PathVariable("id") int id,
                              @RequestParam(value = "full_name" , required = false) String full_name ,
                              @RequestParam(value = "purchase_price" , required = false) String purchase_price ,
                              Model model )
    {
        Car car = carService.getCarById(id);
        if (car != null)
        {
            if (!car.getIspurchase())
            {
                Parameter p = parameterService.getParameterById("profit_ratio");
                double profit_ratio = Double.parseDouble(p.getValue());
                double defult_price = car.getPrice() + ( car.getPrice() * profit_ratio );
                Purchase purchase = new Purchase();
                purchase.setCar(car);
                purchase.setPurchase_price(defult_price);
//              String username = Appuser.getAuthUsername(); Appuser appuser = userService.getUserByUsername(username); purchase.setAppuser(appuser);
                Appuser appuser = userService.getUserByUsername("admin");
                purchase.setAppuser(appuser);
                model.addAttribute("purchase",purchase);
                return "Add_Purchase";
            }
            else
            {
                model.addAttribute("message",String.format("car with id : ( %d ) is purchase before , you can't purchase it again !",id));
                return "Error_Not_Found";
            }
        }
        else
        {
            model.addAttribute("message",String.format("there is no car with id : ( %d ) !",id));
            return "Error_Not_Found";
        }

    }


    @PostMapping("car/store/sale/{id}")
    public String storePurchaseCar(@PathVariable("id") int id, @RequestPart(value = "full_name") String full_name ,
                                   @RequestPart(value = "purchase_price") String purchase_price ,
                                   @RequestPart(value = "car_version") String car_version ,
                                   BindingResult result, Model model)
    {



        System.out.println("full name : "+full_name);
        System.out.println("purchase_price : "+purchase_price);
        System.out.println("car_version : "+car_version);


        if (result.hasErrors())
        {
            System.err.println("valid error !");

            model.addAttribute("message","the car not sale !");
            return "redirect:/allCars";
        }
        else
        {
            Car car = carService.getCarById(id);
            if (car != null)
            {
                if ( car.getVersion() == Integer.parseInt(car_version) )
                {
                    Purchase purchase = new Purchase();
                    Customer customer = new Customer(full_name);
                    Double price = Double.parseDouble(purchase_price);
                    car.setAsPurchase();
//      String username = Appuser.getAuthUsername(); Appuser appuser = userService.getUserByUsername(username); purchase.setAppuser(appuser);
                    Appuser appuser = userService.getUserByUsername("admin");
                    purchase.setAppuser(appuser);
                    purchase.setCustomer(customer);
                    purchase.setPurchase_price(price);
                    purchase.setCar(car);
                    customerService.save(customer);
                    Purchase purchase1 = purchaseService.save(purchase);
                    model.addAttribute("message","the car sale success");
                    logService.save(on_table,purchase1.getId()+"","insert");
                    return "redirect:/allCars";
                }
                else
                {
                    model.addAttribute("message",String.format("Sorry please try again ,there is error in server ,someone make transaction on this car with id ( %d )!",id));
                    return "Error_Not_Found";
                }
            }
            else
            {
                model.addAttribute("message",String.format("there is no car with id : ( %d ) !",id));
                return "Error_Not_Found";
            }


        }


    }

}