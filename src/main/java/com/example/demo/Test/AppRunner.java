package com.example.demo.Test;//package com.example.demo.Test;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AppRunner implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
//
//    private final paraRepo paraRepo;
//
//    public AppRunner(paraRepo paraRepo) {
//        this.paraRepo = paraRepo;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        logger.info(".... Fetching books");
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("number_of_seats"));
//        logger.info("isbn-4567 -->" + paraRepo.findByMykey("profit_ratio"));
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("min_price"));
//        logger.info("isbn-4567 -->" + paraRepo.findByMykey("number_of_seats"));
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("profit_ratio"));
//        logger.info("isbn-1234 -->" + paraRepo.findByMykey("min_price"));
//    }
//
//}
