package com.example.demo.myUtile;

import com.example.demo.Models.Car;

import java.io.PrintWriter;
import java.util.List;

public class CsvUtils {

    public static void downloadCsv(PrintWriter writer, List<Car> cars, Double price) {
        writer.write("Car ID, Name, Price , Number of Seats \n");
        for (Car car : cars) {
            writer.write(car.getId() + "," + car.getName() + "," + car.getPrice() + "," + car.getNumber_of_seats() + "\n");
        }
        writer.write("------------------------------------- \n");
        writer.write("Total price : "+price);
    }

}