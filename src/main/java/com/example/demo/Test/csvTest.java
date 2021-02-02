//package com.example.demo.Test;
//
//import com.example.demo.Models.Purchase;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.lang.reflect.Type;
//import java.util.List;
//
//public class csvTest
//{
//    public static void main(String[] args)
//    {
//        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
//
//        Type listType = new TypeToken<List<Purchase>>() {}.getType();
//
//        List<Purchase> purchases = null;
//
//        FileReader fileReader = null;
//        try
//        {
//            String path = "C:\\Users\\Mohammed Masri\\Desktop\\Internet Application Projects\\internet application project 1\\src\\test\\java\\Data\\Purchase\\purchase data.json";
//
//            fileReader = new FileReader(path);
//            System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssss");
//            purchases = gson.fromJson(fileReader, listType);
//            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//
//            for (Purchase purchase :purchases) {
//                System.out.println(purchase);
//            }
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
