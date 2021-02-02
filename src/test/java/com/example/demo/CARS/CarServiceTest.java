package com.example.demo.CARS;

import com.example.demo.Models.Car;
import com.example.demo.Models.Parameter;
import com.example.demo.Models.Purchase;
import com.example.demo.Repositories.ParameterRepository;
import com.example.demo.Repositories.PurchaseRepository;
import com.example.demo.Services.ParameterService;
import com.example.demo.Services.PurchaseService;
import com.example.demo.Repositories.CarRepository;
import com.example.demo.Services.CarService;
import com.example.demo.myUtile.CsvUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RunWith(SpringRunner.class)
public class CarServiceTest
{

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private PurchaseRepository purchaseRepository;

    @MockBean
    private ParameterRepository parameterRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ParameterService parameterService;

    @TestConfiguration
    static class CarServiceContextConfiguration
    {
        @Bean
        public CarService carService()
        {
            return new CarService();
        }
    }

    @TestConfiguration
    static class PurchaseServiceContextConfiguration
    {
        @Bean
        public PurchaseService purchaseService()
        {
            return new PurchaseService();
        }
    }

    @TestConfiguration
    static class ParameterServiceContextConfiguration
    {
        @Bean
        public ParameterService parameterService()
        {
            return new ParameterService();
        }
    }


    public List<Car> getAllCarsData()
    {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

        Type listType = new TypeToken<List<Car>>() {}.getType();

        List<Car> cars = null;

        FileReader fileReader = null;
        try
        {
            String path = "C:\\Users\\Mohammed Masri\\Desktop\\Internet Application Projects\\internet application project\\src\\test\\java\\Data\\Car\\cars data.json";

            fileReader = new FileReader(path);
            cars = gson.fromJson(fileReader, listType);

            System.out.println(gson.toJson(cars));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public List<Purchase> getAllPurchasesData()
    {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

        Type listType = new TypeToken<List<Purchase>>() {}.getType();

        List<Purchase> purchases = null;

        FileReader fileReader = null;
        try
        {
            String path = "C:\\Users\\Mohammed Masri\\Desktop\\Internet Application Projects\\internet application project\\src\\test\\java\\Data\\Purchase\\purchase data.json";

            fileReader = new FileReader(path);
            purchases = gson.fromJson(fileReader, listType);

            //System.out.println(gson.toJson(purchases));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    public int get_month_from_date(Date date)
    {
        LocalDate currDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int Month = currDate.getMonthValue();
        return Month;
    }




    @Test
    public void getAllCars_Test()
    {

        List<Car> cars = getAllCarsData();

        BDDMockito.given( carRepository.findAll() ).willReturn( cars );

        Assertions.assertThat( carService.getAllCars() )
                .hasSize( cars.size() );
    }

    @Test
    public void getCarById_Test()
    {
        List<Car> cars = getAllCarsData();
        Car c1 = cars.get(0);
        Car c2 = cars.get(1);

        BDDMockito.given(carRepository.findById(ArgumentMatchers.anyInt())).willReturn(Optional.ofNullable(c1));

        Car result = carService.getCarById(1);

        Assertions.assertThat(result).isEqualTo(c1);
    }


    @Test
    public void getCarByName_Test()
    {
        List<Car> cars = getAllCarsData();
        Car c1 = cars.get(0);
        Car c2 = cars.get(1);

        List<Car> result_cars = Arrays.asList(c1 ,c2);

        List<Car> result_cars2 = Arrays.asList(c1 ,c2,c1);

        BDDMockito.given(carRepository.findByNameContains(ArgumentMatchers.anyString())).willReturn(result_cars);

        List<Car> result = carService.getCarByName(ArgumentMatchers.anyString());

        Assertions.assertThat(result).isEqualTo(result_cars);
    }

    @Test
    public void getCarIsNotPurchase_Test()
    {
        List<Car> cars = getAllCarsData();
        Car c1 = cars.get(0);

        int size_before = cars.size();

        c1.setAsPurchase();



        List<Car> result_cars = new ArrayList<>();
        result_cars.addAll(cars);
        result_cars.remove(0);

        int size_after = result_cars.size();

        BDDMockito.given(carRepository.findByIspurchase(false)).willReturn(result_cars);

        List<Car> result = carService.getCarIsNotPurchase();

        Assertions.assertThat(result)
                .isEqualTo(result_cars)
                .hasSize(size_after);
    }


    @Test
    public void saveCar_Test()
    {
        List<Car> cars = getAllCarsData();

        BDDMockito.given(parameterRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.ofNullable(new Parameter("num_of_seats","4")));


        Parameter parameter = parameterService.getParameterById("num_of_seats");


        Car newCar = new Car(1001,"car 1001" , 500 ,Integer.parseInt(parameter.getValue()));

        int size_before = cars.size();

        cars.add(newCar);

        int size_after = cars.size();

        BDDMockito.given(carRepository.save(newCar)).willReturn(newCar);

        BDDMockito.given(carRepository.findById(ArgumentMatchers.anyInt())).willReturn(Optional.ofNullable(newCar));

        BDDMockito.given(carRepository.findAll()).willReturn(cars);


        Assertions.assertThat(carService.getCarById(1001))
                .isEqualTo(newCar);

        Assertions.assertThat(carService.getAllCars())
                .hasSize(size_after)
                .isEqualTo(cars);
    }


    // Error
    @Test
    public void deleteCar_Test()
    {
        List<Car> cars = getAllCarsData();
        int size_before = cars.size();

        Car c1 = cars.get(0);

        cars.remove(c1);

        int size_after = cars.size();




        BDDMockito.given(carRepository.findAll()).willReturn(cars);

        BDDMockito.given( carService.delete(ArgumentMatchers.anyInt()) ).willReturn(c1);

        Car deleted_car = carService.delete(c1.getId());

        //List<Car> result_cars = (List<Car>) carService.getAllCars();

        Assertions.assertThat(deleted_car)
                .isEqualTo(c1);
//
//        Assertions.assertThat(carService.getAllCars())
//                .hasSize(size_after)
//                .isEqualTo(result_cars);
    }


    @Test
    public void updateCar_Test()
    {
        List<Car> cars = getAllCarsData();
        Car c1 = cars.get(0);
        int myId = c1.getId();

        Car oldCar = Car.make_packup(c1);

        c1.update("new "+c1.getName(),c1.getPrice(),c1.getNumber_of_seats(),c1.getIspurchase());

        BDDMockito.given(carRepository.findById(ArgumentMatchers.anyInt())).willReturn(Optional.ofNullable(cars.get(0)));

        Assertions.assertThat(c1).isNotEqualTo(oldCar);

        Assertions.assertThat(carService.getCarById(1)).isEqualTo(c1);


    }





    @Test
    public void MakeInventory_Test()
    {

        int cuurMonth = 12;

        List<Purchase> allPurchases = getAllPurchasesData();

        List<Car> cars = getAllCarsData();

        List<Purchase> purchasesInMonth = new ArrayList<>();

        List<Car> carsInMonth = new ArrayList<>();
        double price = 0;

        for (Purchase p: allPurchases)
        {
            if (p.getMonth() == cuurMonth)
            {
                purchasesInMonth.add(p);
                carsInMonth.add(p.getCar());
                price += p.getPurchase_price();
            }
        }

        FileWriter fileWriter= null;
        try
        {
            fileWriter = new FileWriter("C:\\Users\\Mohammed Masri\\Desktop\\Internet Application Projects\\internet application project\\src\\test\\java\\Data\\CurrResult.csv");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            CsvUtils.downloadCsv(printWriter, carsInMonth , price) ;

            printWriter.close();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }







    }

}
