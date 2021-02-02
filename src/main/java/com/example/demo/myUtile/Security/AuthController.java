package com.example.demo.myUtile.Security;

import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private TokenUtilty tokenUtilty;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping(value = "/login")
    public ModelAndView login()
    {

        ModelAndView modelAndView = new ModelAndView("login/login.html");
        modelAndView.addObject("signinrequest",new SignInRequest());
        return modelAndView;
    }


    @PostMapping(value = "/signin")
    public JwtResponse SignIn(@RequestBody SignInRequest signInRequest)
    {
        System.out.println(signInRequest);
        final Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(signInRequest.getUsername() , signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());

        String _Token = tokenUtilty.generateToken(userDetails);

        JwtResponse response = new JwtResponse(_Token);
        return response;
    }





//    @PostMapping(value = "/signin")
//    public void SignIn(@ModelAttribute SignInRequest signInRequest)
//    {
//        System.out.println(signInRequest);
//    }
}
