package com.example.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(Application.class, args);
    }
}
