package com.example.demo.CARS;

import com.example.demo.Models.Parameter;
import com.example.demo.Services.CustomerService;
import com.example.demo.Models.Car;
import com.example.demo.Repositories.ParameterRepository;
import com.example.demo.Services.PurchaseService;
import com.example.demo.Services.CarService;
import com.example.demo.Services.UserService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CarControllerTest
{

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService carService;



    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private CustomerService customerService ;

    @Autowired
    private UserService userService ;

    @Autowired
    private PurchaseService purchaseService;


    @Test
    public void getallCars_Test() throws Exception
    {
        Car c1 = new Car(1,"car 1",200,4)
                , c2 = new Car(2,"car 2",300,6);
        List<Car> cars = Arrays.asList(c1 ,c2);

        BDDMockito.given(carService.getAllCars()).willReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }




    @GetMapping(value = "/notPurchase/all")
    public ResponseEntity<Iterable<Car>> getallCarsNotPurchase()
    {
        return new ResponseEntity<Iterable<Car>>(this.carService.getCarIsNotPurchase(),HttpStatus.OK);
    }

    @GetMapping(value = "/getbyid/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id)
    {

//        try {
//            Car mycar = this.carRepository.findById(id).get();
//        }
//        catch (NoSuchElementException ex)
//        {
//            throw new NotFoundException(String.format("there is no record with this id (%d) in Database",id));
//        }
//



        Car c = this.carService.getCarById(id);
        if (c != null)
        {
            return new ResponseEntity<Car>(c,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Car>(c,HttpStatus.NOT_FOUND);
        }


    }


    @GetMapping(value = "/getbyname/{name}")
    public ResponseEntity<List<Car>> getCarByName(@PathVariable String name)
    {

        List<Car> c = this.carService.getCarByName(name);
        if (c.size() != 0)
        {
            return new ResponseEntity<List<Car>>(c,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<List<Car>>(c,HttpStatus.NOT_FOUND);
        }

    }





    @PostMapping(value = "/store")
    public ResponseEntity<Car> storeCar(@Valid @RequestBody Car c)
    {

        if (c.getNumber_of_seats() == 0)
        {
            Parameter p = parameterRepository.findById("number_of_seats").orElse(null);
            c.setNumber_of_seats(Integer.parseInt(p.getValue()));
            Car newCar = this.carService.save(c);
            return new ResponseEntity<Car>(newCar, HttpStatus.CREATED);
        }
        else
        {
            Car newCar = this.carService.save(c);
            return new ResponseEntity<Car>(newCar, HttpStatus.CREATED);
        }

    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Car> updateCar(@Valid @RequestBody Car c , @PathVariable int id)
    {

        Car oldc = this.carService.getCarById(id);
        if (oldc != null)
        {
            Car newCar = this.carService.update(c,id);

            //System.out.println("done update "+newCar);

            return new ResponseEntity<Car>(newCar, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Car>(c,HttpStatus.NOT_FOUND);
        }




    }


    @GetMapping(value = "/purchase/{id}")
    public ResponseEntity<Car> getCarToSetPurchase(@PathVariable int id)
    {
        Car oldc = this.carService.getCarById(id);
        if (oldc != null)
        {
            Car newCar = this.carService.update(oldc,id);

            //System.out.println("done update "+newCar);

            return new ResponseEntity<Car>(newCar, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Car>(oldc,HttpStatus.NOT_FOUND);
        }

    }

//    @PostMapping(value = "/purchase/store/{id}")
//    public ResponseEntity<Purchase> SetCarAsPurchase(@RequestBody CustomerAndDouble customerAndDouble  , @PathVariable int id)
//    {
//        System.out.println( customerAndDouble.customer_full_name );
//        System.out.println( customerAndDouble.purchase_price );
//
//
//
//
//        Car car  = this.carService.getCarById(id);
//        if (car != null)
//        {
//            car.setAsPurchase();
//            String username = Appuser.getAuthUsername();
//
//            Appuser user = userService.getUserByUsername(username);
//
//            Customer customer = new Customer(customerAndDouble.customer_full_name );
//
//            customerService.save(customer);
//
//
//            Purchase purchase = new Purchase(customerAndDouble.purchase_price ,car,customer,user);
//
//            purchaseService.save(purchase);
//
//
//
//            return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<Purchase>((Purchase) null,HttpStatus.NOT_FOUND);
//        }
//
//
//    }





    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id)
    {
        System.out.print(id);
        Car c =  this.carService.getCarById(id);
        this.carService.delete(id);

        //System.out.println("done store "+c);

        return new ResponseEntity<Void>( HttpStatus.NO_CONTENT);
    }

}
