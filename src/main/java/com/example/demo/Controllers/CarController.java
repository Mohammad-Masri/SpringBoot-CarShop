package com.example.demo.Controllers;

import com.example.demo.Models.Car;
import com.example.demo.Models.Parameter;
import com.example.demo.Services.CacheParameterService;
import com.example.demo.Services.CarService;
import com.example.demo.Services.LogService;
import com.example.demo.Services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class CarController
{
    private final String on_table = "car";

    @Autowired
    private LogService logService;

    @Autowired
    private CarService carService;

    @Autowired
    private CacheParameterService parameterService;


    @GetMapping(value = "allCars")
    public ModelAndView ShowAllCars()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("AllCars");

        List<Car> cars = new ArrayList<>();

        carService.getAllCars().forEach(cars::add);

        modelAndView.addObject("cars",cars);
        return modelAndView;
    }

    @GetMapping(value = "car/{id}")
    public ModelAndView ShowCar(@PathVariable int id)
    {

        ModelAndView modelAndView = new ModelAndView();


        Car c = this.carService.getCarById(id);

        if (c != null)
        {

            modelAndView.setViewName("Show_Car");
            modelAndView.addObject("car",c);
            return modelAndView;
        }
        else
        {
            modelAndView.setViewName("Error_Not_Found");
            modelAndView.addObject("message",String.format("there is no car with id : ( %d ) !",id));
            return modelAndView;
        }

    }


//    @GetMapping("car/add")
//    public String AddCar(Car car)
//    {
//        int number_of_seats = parameterService.getNumberOfSeats();
//        int min_price =  parameterService.getMinPrice();
//        car.setNumber_of_seats(number_of_seats);
//        car.setPrice(min_price);
//        return "Add_Car";
//    }

    @GetMapping("car/add")
    public String AddCar(Car car)
    {
        Parameter p_number_of_seats = parameterService.getParameterById("number_of_seats");
        Parameter p_min_price =  parameterService.getParameterById("min_price");

        Integer number_of_seats = Integer.parseInt(p_number_of_seats.getValue());
        Double min_price = Double.parseDouble(p_min_price.getValue());

        car.setNumber_of_seats(number_of_seats);
        car.setPrice(min_price);
        return "Add_Car";
    }



    @PostMapping("car/store")
    public String StoreCar(@Valid Car car, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            System.err.println("valid error !");
            return "Add_Car";
        }

        Car newcar = carService.save(car);


        List<Car> cars = new ArrayList<>();
        carService.getAllCars().forEach(cars::add);
        model.addAttribute("cars", cars);

        logService.save(on_table,newcar.getId()+"","insert");

        return "redirect:/allCars";
    }




    @GetMapping("car/edit/{id}")
    public String editCar(@PathVariable("id") int id, Model model)
    {
        Car car = carService.getCarById(id);

        if (car != null)
        {
            System.out.println("car up to view : "+car);
            model.addAttribute("car", car);
            return "Update_Car";
        }
        else
        {
            model.addAttribute("message",String.format("there is no car with id : ( %d ) !",id));
            return "Error_Not_Found";
        }

    }

    @PostMapping("car/update/{id}")
    public String updateCar(@PathVariable("id") int id, @Valid Car car, BindingResult result, Model model)
    {

        if (result.hasErrors())
        {
            System.err.println("valid error !");

            Car oldCar = carService.getCarById(id);
            model.addAttribute("car",oldCar);

            return "Update_Car";
        }

        System.out.println("car from view : "+car);
        Car oldCar = carService.getCarById(id);

        if (oldCar != null)
        {
            if (oldCar.getVersion() == car.getVersion())
            {
                Car newCar = carService.update(car,id);

                List<Car> cars = new ArrayList<>();
                carService.getAllCars().forEach(cars::add);
                model.addAttribute("cars", cars);

                logService.save(on_table,newCar.getId()+"","update");

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

    @GetMapping("car/delete/{id}")
    public String deleteCar(@PathVariable("id") int id, Model model)
    {
        Car car = carService.getCarById(id);

        if (car != null)
        {
            carService.delete(id);

            List<Car> cars = new ArrayList<>();
            carService.getAllCars().forEach(cars::add);
            model.addAttribute("cars", cars);

            logService.save(on_table,id+"","delete");

            return "redirect:/allCars";
        }
        else
        {
            model.addAttribute("message",String.format("there is no car with id : ( %d ) !",id));
            return "Error_Not_Found";
        }











    }

}
