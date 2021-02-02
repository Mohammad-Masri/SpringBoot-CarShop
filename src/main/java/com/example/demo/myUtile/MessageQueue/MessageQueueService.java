package com.example.demo.myUtile.MessageQueue;


import com.example.demo.Models.Car;
import com.example.demo.Repositories.CarRepository;
import com.example.demo.Models.Purchase;
import com.example.demo.Repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class MessageQueueService
{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public int get_month_from_date(Date date)
    {
        LocalDate currDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int Month = currDate.getMonthValue();
        return Month;
    }

    public Node get_car_ids(int month , List<Purchase> purchaseList )
    {
        Node n = new Node();

        List<Integer> car_ids = new ArrayList<>();
        double purches_price_for_all_cars = 0;

        for (int i = 0 ; i < purchaseList.size() ; i++)
        {
            Purchase purchase = purchaseList.get(i);

            Date purchaseDate = purchase.getCreated_at();

            int purchaseMonth = get_month_from_date(purchaseDate);

            //System.out.println("purchase Month : "+purchaseMonth);

            if (purchaseMonth == month)
            {
                Car car = purchase.getCar();

                purches_price_for_all_cars += purchase.getPurchase_price();

                car_ids.add(car.getId());
            }

        }


        n.integers = car_ids;
        n.numDouble = purches_price_for_all_cars;

        return n;
    }

//    public List<Car> Make_an_inventory_of_sales_operations(Date date)
//    {
//
//        List<Purchase> purchaseList = (List<Purchase>) purchaseRepository.findAll();
//        List<Integer> car_ids = null;
//        double purches_price_for_all_cars ;
//
//
//        int cuurMonth = get_month_from_date(date);
//
//        //System.out.println("cuur Month : "+cuurMonth);
//
//
//        Node n = get_car_ids(cuurMonth , purchaseList);
//        car_ids = n.integers;
//        purches_price_for_all_cars = n.numDouble;
//
//        Iterable<Car> cars = carRepository.findAllById(car_ids);
//        List<Car> carList = new ArrayList<>();
//
//        cars.forEach(carList::add);
//
//        System.out.println(purches_price_for_all_cars);
//
//        return carList;
//    }



    @Transactional
    public Inventory_Result Make_an_inventory_of_sales_operations(Date date)
    {
        int cuurMonth = get_month_from_date(date);

        List<Purchase> purchases = purchaseRepository.findPurchaseInMonthWithPessimisticLock(cuurMonth);

        List<Integer> car_ids = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        double purches_price_for_all_cars = 0;


        for (Purchase p : purchases)
        {
            car_ids.add(p.getId());
            cars.add(p.getCar());
            purches_price_for_all_cars += p.getPurchase_price();
        }

        Inventory_Result inventory_result = new Inventory_Result(cars,purches_price_for_all_cars);
        return inventory_result;
    }


}
