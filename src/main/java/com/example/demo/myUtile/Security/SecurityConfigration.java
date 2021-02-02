package com.example.demo.myUtile.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter
{

    public final String[] PUBLIC_URLS = {
            "/home/index" , "/home/download/cars.csv" , "/cars/all" , "/users/store" ,
            "/auth/signin" ,"/auth" ,"/auth/login" , "/test/", "**"
    };

    @Bean
    AuthFilter authFilter()
    {
        return new AuthFilter();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we will use JWT : Json Web Token
        http.
                // Authentication based on session and cookies disable
                cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                // set url as public or you must be auth
                .authorizeRequests()
                    // set url as public you want
                    .antMatchers(PUBLIC_URLS).permitAll() // this url is public
                    // then set else url as auth
                    .anyRequest().authenticated()
                    .and()
                // before each request the app will call auth filter
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)

        ;
    }


}
