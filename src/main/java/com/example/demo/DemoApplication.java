package com.example.demo;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableCaching
@RestController
public class DemoApplication implements CommandLineRunner
{
	@Value("${server.port}")
	private String port;


	@Autowired
	HikariDataSource dataSource;



	public static void main(String[] args)
	{

		SpringApplication.run(DemoApplication.class, args);
	}


	@GetMapping("/test/invoke/app")
	public String invoke()
	{
		return "Hello from app with port "+port;
	}




	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("spring.datasource.hikari.maximum-pool-size :"+dataSource.getMaximumPoolSize());
	}
}
