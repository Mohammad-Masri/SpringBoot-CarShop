package com.example.demo.Controllers;

import com.example.demo.Models.Car;
import com.example.demo.Services.CarService;
import com.example.demo.Models.Customer;
import com.example.demo.Services.CustomerService;
import com.example.demo.myUtile.MessageQueue.Inventory_Result;
import com.example.demo.myUtile.MessageQueue.MessageQueueService;
import com.example.demo.Services.ParameterService;
import com.example.demo.Models.Purchase;
import com.example.demo.Services.PurchaseService;
import com.example.demo.Models.Appuser;
import com.example.demo.Services.UserService;
import com.example.demo.myUtile.CsvUtils;
import com.example.demo.myUtile.MessageQueue.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping(value = "/")
public class HomeController
{
    @Autowired
    private CarService carService;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    MessageQueueService messageQueueService;

    @GetMapping(value = "index")
    public ModelAndView index()
    {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }





//    @GetMapping(value = "/download/cars.csv")
//    public String make_inventory(HttpServletResponse response , Model model) throws IOException
//    {
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; file=cars.csv");
//
//        Inventory_Result inventory_result = messageQueueService.Make_an_inventory_of_sales_operations(new Date());
//
//        CsvUtils.downloadCsv(response.getWriter(), inventory_result.getCars() , inventory_result.getPrice()) ;
//
//        model.addAttribute("message",String.format("Do a Statistic Done"));
//
//        return "redirect:/allCars";
//
//    }



    @GetMapping(value = "/download/cars.csv")
    public String make_inventory( Model model) throws IOException
    {

        try
        {
            Send.send_message();

        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        model.addAttribute("message",String.format("Do a Statistic Done"));

        return "redirect:/allCars";

    }











}
