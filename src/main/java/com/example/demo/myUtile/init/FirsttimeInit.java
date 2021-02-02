//package com.example.demo.myUtile.init;
//
//import com.example.demo.Models.Appuser;
//import com.example.demo.Repositories.UserRepository;
//import com.example.demo.Services.UserService;
//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FirsttimeInit implements CommandLineRunner{
//
//    private final Log log = LogFactory.getLog(FirsttimeInit.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // at run event
//        if (userRepository.findAll() != null)
//        {
//            Appuser appuser = new Appuser(1,"admin user","admin","123456789");
//            log.info("No User in App , we create user for you :\n"+appuser);
//            appuser.setPassword(userService.passwordEncoder().encode(appuser.getPassword()));
//            userService.save(appuser);
//        }
//        System.out.println("Hello In My App");
//    }
//}
